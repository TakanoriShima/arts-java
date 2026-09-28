// レジメ/ の Markdown 教材を、学生配布用の PDF（pdf/）に変換する。
// 依存パッケージなし：Node.js 18 以上と、Google Chrome または Microsoft Edge だけを使う。
//
//   node scripts/build-pdf.mjs            … レジメ/ の全ファイルを変換
//   node scripts/build-pdf.mjs 16 23      … 指定した回だけ変換
//   node scripts/build-pdf.mjs --keep-html … 確認用の HTML（.pdf-build/）を残す
//
// ブラウザの場所は、環境変数 CHROME_PATH で指定できる（未指定なら標準のインストール先を探す）。

import fs from "node:fs";
import path from "node:path";
import { spawnSync } from "node:child_process";
import { fileURLToPath, pathToFileURL } from "node:url";

const ROOT = path.resolve(path.dirname(fileURLToPath(import.meta.url)), "..");
const SRC_DIR = path.join(ROOT, "レジメ");
const OUT_DIR = path.join(ROOT, "pdf");
const WORK_DIR = path.join(ROOT, ".pdf-build");

// ---------------------------------------------------------------- Markdown → HTML

function escapeHtml(s) {
  return s.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
}

// インライン要素：`code` と **太字**（太字の中に code があってもよい）
function inline(text) {
  const codes = [];
  const withMarks = text.replace(/`([^`]+)`/g, (_, c) => {
    codes.push("<code>" + escapeHtml(c) + "</code>");
    return "\u0000" + (codes.length - 1) + "\u0000";
  });
  return escapeHtml(withMarks)
    .replace(/\*\*(.+?)\*\*/g, "<strong>$1</strong>")
    .replace(/\u0000(\d+)\u0000/g, (_, n) => codes[Number(n)]);
}

// 日本語どうしの改行は詰め、英数字どうしは空白でつなぐ
function joinLines(lines) {
  let s = "";
  for (const line of lines) {
    const t = line.trim();
    if (s === "") {
      s = t;
    } else if (/[\x21-\x7e]$/.test(s) && /^[\x21-\x7e]/.test(t)) {
      s += " " + t;
    } else {
      s += t;
    }
  }
  return s;
}

const LIST_RE = /^(\s*)([-*]|\d+\.)\s+(.*)$/;

function renderList(lines) {
  // lines: 連続したリスト行。インデント 2 以上は入れ子
  let html = "";
  const stack = []; // { indent, tag }
  for (const line of lines) {
    const m = line.match(LIST_RE);
    const indent = m[1].length;
    const tag = /\d+\./.test(m[2]) ? "ol" : "ul";
    while (stack.length && indent < stack[stack.length - 1].indent) {
      html += "</li></" + stack.pop().tag + ">";
    }
    if (!stack.length || indent > stack[stack.length - 1].indent) {
      const start = tag === "ol" ? ` start="${parseInt(m[2], 10)}"` : "";
      html += `<${tag}${start}><li>`;
      stack.push({ indent, tag });
    } else {
      html += "</li><li>";
    }
    html += inline(m[3]);
  }
  while (stack.length) html += "</li></" + stack.pop().tag + ">";
  return html;
}

function splitRow(line) {
  let s = line.trim();
  if (s.startsWith("|")) s = s.slice(1);
  if (s.endsWith("|")) s = s.slice(0, -1);
  return s.split("|").map((c) => c.trim());
}

function renderTable(lines) {
  const head = splitRow(lines[0]);
  const aligns = splitRow(lines[1]).map((c) => (c.endsWith(":") ? (c.startsWith(":") ? "center" : "right") : ""));
  const attr = (i) => (aligns[i] ? ` style="text-align:${aligns[i]}"` : "");
  // 短いセル（番号・時間・用語など）は、途中で折り返さない
  const cls = (c) => (c.replace(/`|\*\*/g, "").length <= 8 ? ' class="short"' : "");
  let html = "<table><thead><tr>" + head.map((c, i) => `<th${attr(i)}${cls(c)}>${inline(c)}</th>`).join("") + "</tr></thead><tbody>";
  for (const line of lines.slice(2)) {
    html += "<tr>" + splitRow(line).map((c, i) => `<td${attr(i)}${cls(c)}>${inline(c)}</td>`).join("") + "</tr>";
  }
  return html + "</tbody></table>";
}

function renderMarkdown(md) {
  const lines = md.replace(/\r\n/g, "\n").replace(/<!--[\s\S]*?-->/g, "").split("\n");
  const out = [];
  let i = 0;
  while (i < lines.length) {
    const line = lines[i];
    const trimmed = line.trim();

    if (trimmed === "") { i++; continue; }

    // コードブロック
    const fence = trimmed.match(/^```(\w*)/);
    if (fence) {
      const lang = fence[1] || "text";
      const code = [];
      i++;
      while (i < lines.length && !lines[i].trim().startsWith("```")) code.push(lines[i++]);
      i++;
      // 短いコードはページをまたがないようにする。長いコードはページをまたいでよい
      const cls = code.length <= 40 ? " keep" : "";
      // 1 行ずつブロックにし、右端で折り返した続きは、その行のインデント + 4 文字の位置から始める
      const body = code
        .map((l) => {
          const indent = l.match(/^ */)[0].length;
          return `<span class="ln" style="--i:${indent}">${l === "" ? " " : escapeHtml(l)}</span>`;
        })
        .join("");
      out.push(`<pre class="code lang-${lang}${cls}"><code>${body}</code></pre>`);
      continue;
    }

    // details / summary → 静的な「答え」の枠
    if (trimmed === "<details>") { out.push('<section class="answer">'); i++; continue; }
    if (trimmed === "</details>") { out.push("</section>"); i++; continue; }
    const summary = trimmed.match(/^<summary>(.*)<\/summary>$/);
    if (summary) { out.push(`<div class="answer-title">${inline(summary[1])}</div>`); i++; continue; }

    // 見出し
    const h = line.match(/^(#{1,4})\s+(.*)$/);
    if (h) {
      const level = h[1].length;
      out.push(`<h${level}>${inline(h[2])}</h${level}>`);
      i++;
      continue;
    }

    // 水平線
    if (/^-{3,}$/.test(trimmed)) { out.push("<hr>"); i++; continue; }

    // 表
    if (trimmed.startsWith("|") && i + 1 < lines.length && /^\|?\s*:?-{3,}/.test(lines[i + 1].trim())) {
      const rows = [];
      while (i < lines.length && lines[i].trim().startsWith("|")) rows.push(lines[i++]);
      out.push(renderTable(rows));
      continue;
    }

    // リスト
    if (LIST_RE.test(line)) {
      const items = [];
      while (i < lines.length && LIST_RE.test(lines[i])) items.push(lines[i++]);
      out.push(renderList(items));
      continue;
    }

    // 段落
    const para = [];
    while (
      i < lines.length &&
      lines[i].trim() !== "" &&
      !/^```|^#{1,4}\s|^-{3,}$|^<\/?details>|^<summary>/.test(lines[i].trim()) &&
      !LIST_RE.test(lines[i]) &&
      !(lines[i].trim().startsWith("|") && i + 1 < lines.length && /^\|?\s*:?-{3,}/.test(lines[i + 1].trim()))
    ) {
      para.push(lines[i++]);
    }
    out.push(`<p>${inline(joinLines(para))}</p>`);
  }
  return out.join("\n");
}

// ---------------------------------------------------------------- HTML テンプレート

function cssString(s) {
  return '"' + s.replace(/\\/g, "\\\\").replace(/"/g, '\\"') + '"';
}

function buildHtml(title, body) {
  return `<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>${escapeHtml(title)}</title>
<style>
@page {
  size: A4;
  margin: 16mm 15mm 18mm 15mm;
  @bottom-left { content: ${cssString(title)}; font-size: 8pt; color: #666; }
  @bottom-right { content: counter(page) " / " counter(pages); font-size: 8pt; color: #666; }
}
html { -webkit-print-color-adjust: exact; print-color-adjust: exact; }
body {
  font-family: "BIZ UDPGothic", "Yu Gothic", "YuGothic", "Meiryo", "Hiragino Sans", "Noto Sans CJK JP", "Noto Sans JP", sans-serif;
  font-size: 10.5pt;
  line-height: 1.7;
  color: #111;
  margin: 0;
  line-break: strict;
}
h1 { font-size: 18pt; margin: 0 0 12pt; padding-bottom: 4pt; border-bottom: 2px solid #333; }
h2 { font-size: 14pt; margin: 20pt 0 8pt; padding: 3pt 8pt; background: #eef1f5; border-left: 5px solid #445; }
h3 { font-size: 12pt; margin: 16pt 0 6pt; padding-bottom: 2pt; border-bottom: 1px solid #999; }
h4 { font-size: 11pt; margin: 12pt 0 4pt; }
h1, h2, h3, h4 { break-after: avoid; page-break-after: avoid; }
p { margin: 6pt 0; }
ul, ol { margin: 4pt 0 6pt; padding-left: 20pt; }
li { margin: 2pt 0; }
li > ul, li > ol { margin: 2pt 0; }
hr { border: none; border-top: 1px dashed #999; margin: 16pt 0; }
strong { font-weight: bold; }
code {
  font-family: "Consolas", "Menlo", "DejaVu Sans Mono", "BIZ UDGothic", "MS Gothic", "Osaka-Mono", "Noto Sans Mono CJK JP", monospace;
  font-size: 0.92em;
  background: #f0f0f0;
  border: 1px solid #ddd;
  border-radius: 3px;
  padding: 0 3px;
  word-break: normal;
  overflow-wrap: anywhere;
}
pre.code {
  font-family: "Consolas", "Menlo", "DejaVu Sans Mono", "BIZ UDGothic", "MS Gothic", "Osaka-Mono", "Noto Sans Mono CJK JP", monospace;
  font-size: 8.8pt;
  line-height: 1.5;
  background: #f7f7f7;
  border: 1px solid #bbb;
  border-left: 4px solid #777;
  padding: 6pt 8pt;
  margin: 6pt 0 8pt;
  white-space: pre-wrap;
  word-break: normal;
  overflow-wrap: anywhere;
}
pre.code code { font: inherit; background: none; border: none; padding: 0; word-break: normal; }
pre.code .ln { display: block; padding-left: calc((var(--i) + 4) * 1ch); text-indent: calc((var(--i) + 4) * -1ch); }
pre.lang-text { background: #fff; border-left-color: #aaa; }
pre.keep { break-inside: avoid; page-break-inside: avoid; }
p:has(+ pre.code) { break-after: avoid; page-break-after: avoid; }
table { border-collapse: collapse; width: 100%; margin: 6pt 0 10pt; font-size: 9.5pt; table-layout: auto; }
th, td { border: 1px solid #999; padding: 3pt 5pt; vertical-align: top; overflow-wrap: anywhere; }
th { background: #eef1f5; }
th.short, td.short { white-space: nowrap; }
thead { display: table-header-group; }
tr { break-inside: avoid; page-break-inside: avoid; }
section.answer { border: 1.5px solid #555; border-radius: 4px; padding: 4pt 10pt 6pt; margin: 12pt 0; background: #fcfcf6; }
.answer-title { break-after: avoid; page-break-after: avoid; font-weight: bold; font-size: 10.5pt; border-bottom: 1px solid #999; margin-bottom: 4pt; padding-bottom: 2pt; }
.answer-title::before { content: "■ "; }
</style>
</head>
<body>
${body}
</body>
</html>
`;
}

// ---------------------------------------------------------------- ブラウザで PDF 化

function findBrowser() {
  if (process.env.CHROME_PATH) return process.env.CHROME_PATH;
  const pf = process.env["ProgramFiles"] || "C:\\Program Files";
  const pf86 = process.env["ProgramFiles(x86)"] || "C:\\Program Files (x86)";
  const local = process.env["LOCALAPPDATA"] || "";
  const candidates = [
    path.join(pf, "Google", "Chrome", "Application", "chrome.exe"),
    path.join(pf86, "Google", "Chrome", "Application", "chrome.exe"),
    path.join(local, "Google", "Chrome", "Application", "chrome.exe"),
    path.join(pf86, "Microsoft", "Edge", "Application", "msedge.exe"),
    path.join(pf, "Microsoft", "Edge", "Application", "msedge.exe"),
    "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome",
    "/Applications/Microsoft Edge.app/Contents/MacOS/Microsoft Edge",
    "/usr/bin/google-chrome",
    "/usr/bin/chromium",
    "/usr/bin/chromium-browser",
  ];
  const found = candidates.find((p) => p && fs.existsSync(p));
  if (!found) throw new Error("Chrome / Edge が見つからない。環境変数 CHROME_PATH にブラウザの実行ファイルを指定する。");
  return found;
}

function printToPdf(browser, htmlFile, pdfFile) {
  const args = [
    "--headless=new",
    "--disable-gpu",
    "--no-first-run",
    "--disable-extensions",
    "--no-pdf-header-footer",
    `--user-data-dir=${path.join(WORK_DIR, "profile")}`,
    `--print-to-pdf=${pdfFile}`,
    pathToFileURL(htmlFile).href,
  ];
  const r = spawnSync(browser, args, { encoding: "utf8", timeout: 120000 });
  if (r.status !== 0 || !fs.existsSync(pdfFile)) {
    throw new Error(`PDF の作成に失敗：${path.basename(htmlFile)}\n${r.stderr || ""}`);
  }
}

function countPages(pdfFile) {
  const text = fs.readFileSync(pdfFile).toString("latin1");
  return (text.match(/\/Type\s*\/Page(?!s)/g) || []).length;
}

// ---------------------------------------------------------------- メイン

const args = process.argv.slice(2);
const keepHtml = args.includes("--keep-html");
const lessons = args.filter((a) => /^\d+$/.test(a));

const files = fs
  .readdirSync(SRC_DIR)
  .filter((f) => /^第\d+回_.*\.md$/.test(f))
  .filter((f) => !lessons.length || lessons.includes(f.match(/^第(\d+)回/)[1]))
  .sort((a, b) => parseInt(a.match(/\d+/)[0], 10) - parseInt(b.match(/\d+/)[0], 10));

if (!files.length) {
  console.error("変換する Markdown が見つからない。");
  process.exit(1);
}

fs.mkdirSync(OUT_DIR, { recursive: true });
fs.mkdirSync(WORK_DIR, { recursive: true });
const browser = findBrowser();
console.log("ブラウザ：" + browser);

for (const file of files) {
  const md = fs.readFileSync(path.join(SRC_DIR, file), "utf8");
  const h1 = md.match(/^#\s+(.*)$/m);
  const title = h1 ? h1[1].trim() : file.replace(/\.md$/, "");
  const base = file.replace(/\.md$/, "");
  const htmlFile = path.join(WORK_DIR, base + ".html");
  const pdfFile = path.join(OUT_DIR, base + ".pdf");
  fs.writeFileSync(htmlFile, buildHtml(title, renderMarkdown(md)), "utf8");
  printToPdf(browser, htmlFile, pdfFile);
  const size = fs.statSync(pdfFile).size;
  console.log(`${base}.pdf  ${countPages(pdfFile)} ページ  ${Math.round(size / 1024)} KB`);
}

if (!keepHtml) fs.rmSync(WORK_DIR, { recursive: true, force: true });
