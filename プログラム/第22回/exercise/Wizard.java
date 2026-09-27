// 魔法使い：HP は低いが、魔法で大きなダメージを与える
public class Wizard extends Character {
    public Wizard(String name, int hp, int power) {
        super(name, hp, power);
    }

    // TODO 演習２-１：attack をオーバーライドし、攻撃力の 2 倍の「火の魔法」にする
    //   表示の例：魔法使いは火の魔法をとなえた！ オークに10のダメージ
}
