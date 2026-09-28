# Java 後期教材制作：Claude Code 作業ルール

## 1. 最初に確認するもの

作業開始時に必ず以下を確認する。

1. AGENTS.md
2. この CLAUDE.md
3. memo.txt（存在する場合）
4. materials/template.md（教材の書式確認が必要な場合）
5. ユーザーから今回直接与えられた指示

教育方針・カリキュラム設計については
AGENTS.md を正本として扱う。

AGENTS.md と CLAUDE.md に矛盾がある場合、
勝手に解釈して作業を進めず、
ユーザーへ報告する。

最新のユーザーの明示的な指示を最優先する。

## 2. 現在の状態

現在は「第 2 版教材の講師確認フェーズ」である（詳細は AGENTS.md 第 3 節）。

- 2026-09-28、講師が第 2 版の教材内容を確定し、学生配布用 PDF（`pdf/`）を作成した。Pleiades 上での確認と、暫定の Pleiades 構成の確定はまだ行っていない。
- 第 2 版のロードマップ・設計判断の正本：`materials/roadmap-v2.md`
- `materials/review-v1.md` は第 1 版のレビュー記録。現在の教材の説明として使わない。
- 第 1 版の教材は `旧教材/第1版/`（参照用）。

ユーザーから明示的な指示があるまで、

- 第 2 版を講師承認済みの正式版として扱わない
- ロードマップ・回の順序を独自に大きく変更しない
- 後続の版を自動生成しない
- 暫定の Pleiades 構成・package 構成（AGENTS.md 第 15 節）を独自に変更しない

## 3. Claude Code の役割

Claude Code は主に、

- プロジェクト全体の確認
- 教育設計のレビュー
- ファイル間の矛盾確認
- 教材の横断レビュー
- Markdown 教材の作成・修正
- Java コードの作成・修正
- 技術検証
- Git 差分確認

を担当する。

ただし、
技術的に正しいことだけを理由に
教材として適切と判断しない。

必ず、

- 学生の難易度
- 未学習事項
- 90 分の授業時間
- 説明量
- 演習量
- 前後回との依存関係
- 学生自身が考える量
- 最終的な自力拡張への接続

も確認する。

## 4. ファイル編集

通常のテキストファイル編集には
Claude Code の Read / Edit / Write を使用する。

Markdown、HTML、CSS、JavaScript、Java、
CLAUDE.md、AGENTS.md 等の通常編集に、

- sed
- awk
- perl

を使用しない。

単純なテキスト編集のためだけに
Bash を使用しない。

可能な場合は Edit を優先する。

Bash は主に、

- Git
- Java のコンパイル・実行
- Python スクリプト
- テスト
- その他 CLI での技術検証

に使用する。

## 5. 変更範囲

ユーザーが指定したファイル・範囲だけを変更する。

「このファイルだけ」
「AGENTS.md だけ」
などの指定がある場合、
他ファイルを変更しない。

問題を発見しても、
依頼範囲外なら勝手に修正せず報告する。

## 6. PLAN ONLY / REVIEW ONLY

ユーザーが、

- PLAN ONLY
- REVIEW ONLY
- 計画だけ
- レビューだけ
- 精査だけ

と指示した場合、

教材ファイル、コード、設定ファイルを変更しない。

問題点、理由、推奨対応を報告する。

必要に応じて reply.txt への回答保存のみ行う。

## 7. 教材作成時

教材制作フェーズへ移行した後は、
レジメ作成前に `materials/template.md` を確認する。

Java 教材も Web 教材と、

- Markdown 構造
- 口調
- 説明の粒度
- コードの見せ方
- 演習形式
- ヒント
- 注意事項

を統一する。

「読むだけ」の教材にせず、

説明
→ 予想
→ 実行
→ 実装
→ 変更
→ 確認

につながる構成にする。

## 8. Java コードの検証

Java コード作成後は、
可能な範囲で CLI によるコンパイル・実行確認を行う。

ただし、
CLI 検証は技術確認であり、
Pleiades での授業利用確認とは区別する。

報告時は、

- CLI 検証済み
- Pleiades 確認未実施
- Pleiades 確認済み

を区別する。

未実施の確認を
実施済みと報告しない。

## 9. Pleiades

実際の授業では Eclipse（Pleiades）を使用する。

第 2 版では、AGENTS.md 第 15 節の暫定構成（1 回につき `javaNN_sample` / `javaNN_exercise` の 2 プロジェクト、第 25 回から `battle` パッケージ）を採用している。講師が Pleiades で確認するまでは確定扱いにしない。

承認なく、

- 暫定構成を変更する
- Eclipse プロジェクト化する（`.project` などを置く）
- フォルダを大規模変更する

ことは禁止する。

## 10. Git

commit / push は
ユーザーから明示的に指示された場合のみ行う。

commit の許可は push の許可を含まない。

Git 操作前後に status / diff を確認する。

ユーザーの既存変更を
勝手に restore / reset / checkout しない。

memo.txt / reply.txt は
.gitignore の追跡対象外を維持する。

## 11. 回答

長いレビュー・設計結果は
reply.txt へ全文を保存する。

ターミナルには、

- 結論
- 重要な問題
- 次に決めること

を簡潔に表示する。

ユーザーが reply.txt を更新しないよう指示した場合は、
その指示を優先する。

## 12. リポジトリのフォルダ構成

リポジトリ内のファイルは、次の役割で置き分ける。

```
materials/
    教材設計資料（curriculum-map.md、learning-objectives.md、template.md など）

レジメ/
    学生配布用 Markdown 教材（第NN回_タイトル.md）

プログラム/
    各回の sample / exercise / answer（プログラム/第NN回/sample/ など）

pdf/
    レジメ/ から生成した学生配布用 PDF（第NN回_タイトル.pdf）

scripts/
    PDF 生成スクリプト（build-pdf.mjs）と手順（README.md）

旧教材/
    過去の版の教材（参照用）
```

### PDF の再生成

- `pdf/` の PDF は `レジメ/` から生成したもの。PDF を直接編集しない。
- レジメを変更したときは、`node scripts/build-pdf.mjs`（1 回分だけなら `node scripts/build-pdf.mjs 16` のように回番号を指定）で PDF を作り直し、レジメと同じコミットに含める。
- 必要な環境は Node.js 18 以上と Chrome / Edge だけ（npm パッケージは使わない）。詳細は `scripts/README.md`。
- 見た目の調整（フォント・改ページ・コードの折り返しなど）は `scripts/build-pdf.mjs` 側で行い、レジメの本文は変えない。
- 作業用フォルダ `.pdf-build/` は自動で削除され、`.gitignore` で除外している。

sample・exercise・answer の役割は AGENTS.md 第 17 節による。

新しい授業教材を作成するときも、この構成を使用する。

授業用のレジメや Java コードを materials へ置かない。

レジメ内でコードファイルを示すときは、
`プログラム/第NN回/sample/Main.java` のように
リポジトリ内の位置がわかる表記にする。

Pleiades のプロジェクト構成・package 構成は第 9 節による。

検証用の一時ファイル（`.class` など）は、リポジトリ内の一時ディレクトリ（例：`.verify/`、`.git/info/exclude` で除外）で扱い、コミットしない。リポジトリ外の Temp ディレクトリは使わない。
