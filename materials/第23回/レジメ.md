# 第23回　ArrayListでメンバーを増減する

## 今日の目的

配列とArrayListの違いを確認し、メンバーの追加・削除が必要な場面でArrayListを利用します。

## 前回とのつながり

第22回では、要素数を最初に決めた配列を使いました。今回は、途中でメンバーが増えるリストを扱います。

## 新しく使うJava

- `ArrayList`
- `add`
- `get`
- `size`
- `remove`

```java
ArrayList<String> items = new ArrayList<>();
items.add("Potion");
System.out.println(items.size());
```

型名を`<>`で指定すると、何を入れるリストか分かりやすくなります。

## サンプルを読む・予想する

add、removeの前後でsizeがどう変わるか予想します。配列との違いを言葉にします。

## 演習

1. `ArrayList<Character>`を作る。
2. Characterを3人追加する。
3. 拡張forで表示する。
4. 1人を削除して、人数を表示する。

## 振り返り

- 配列とArrayListを使い分ける理由。
- `size()`と配列の`length`の違い。
- APIの使い方を調べながら変更できたか。

## 発展

アイテム名を`ArrayList<String>`で管理します。

