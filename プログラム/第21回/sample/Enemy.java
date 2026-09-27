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
}
