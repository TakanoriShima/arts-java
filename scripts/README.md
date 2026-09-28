# レジメの PDF 生成

`レジメ/` の Markdown 教材から、学生配布用の PDF を `pdf/` に作る。

## 必要な環境

- Node.js 18 以上（追加のパッケージは不要。`npm install` はしない）
- Google Chrome または Microsoft Edge
  - 標準のインストール先から自動で探す。見つからない場合は、環境変数 `CHROME_PATH` にブラウザの実行ファイルを指定する。
- 日本語フォント（Windows 標準の BIZ UDPゴシック・游ゴシック・メイリオなど。コードは Consolas と日本語等幅フォント）

## コマンド

リポジトリのルートで実行する。

```
node scripts/build-pdf.mjs            # 第16回〜第30回の 15 冊を一括生成
node scripts/build-pdf.mjs 16 23      # 指定した回だけ生成
node scripts/build-pdf.mjs --keep-html  # 確認用の HTML を .pdf-build/ に残す
```

## 出力先

`pdf/第NN回_タイトル.pdf`（Markdown と同じファイル名）

## メモ

- `<details><summary>` の答えは、PDF では「■ 答え」の枠として常に表示する。
- `<!-- verify: ... -->`（検証用のコメント）は PDF に出さない。
- 作業用フォルダ `.pdf-build/` は生成後に自動で削除される（`.gitignore` 対象）。
