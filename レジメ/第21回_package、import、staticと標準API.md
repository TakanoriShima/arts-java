# 第21回　package、import、staticと標準API

## 今日の目的

クラスをpackageで整理し、importで標準APIを利用します。`static`は、インスタンスごとではなくクラスで共有する処理として読み取ります。

## 前回とのつながり

第20回では複数のCharacterをまとめて処理しました。今回はクラスが増えたときに、名前を整理して利用する方法を学びます。

## 新しく使うJava

- `package`
- `import`
- `static`
- `java.util.Random`

package名とフォルダの関係は、Pleiadesで実際に作成するときに確認します。今回のsampleとexerciseは別packageです。

## サンプルを読む・予想する

`sample.Main`が`Random`をどのように利用しているか読みます。乱数の結果は毎回同じか、違うかを予想します。

## 演習

1. `exercise`のpackage宣言を確認する。
2. `Random`を使って攻撃力を決める。
3. `BattleUtil`のstaticメソッドを呼び出す。
4. Consoleに結果を表示する。

package名、import文、フォルダの場所が合っているか確認します。

## 振り返り

- packageは何を整理するか。
- importは何を短く書くためのものか。
- staticメソッドはどのように呼び出すか。

## 発展

乱数の範囲を変更し、攻撃力の最大値と最小値を確認します。

