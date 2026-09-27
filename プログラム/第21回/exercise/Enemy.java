// 敵：Character の機能をすべて受けつぎ、たおすともらえる経験値を持つ
public class Enemy extends Character {
    private final int exp;

    public Enemy(String name, int hp, int power, int exp) {
        super(name, hp, power);
        this.exp = exp;
    }

    public int getExp() {
        return exp;
    }

    // TODO 演習２-１：attack をオーバーライドし、4 ターンごとに「強攻撃」（攻撃力の 2 倍）にする
    //               それ以外のターンは、親クラスのふつうの攻撃（super.attack）を使う
}
