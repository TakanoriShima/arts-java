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

    // 敵の攻撃：4 ターンごとに強攻撃（2 倍）
    @Override
    public void attack(Character target, int turn) {
        if (turn % 4 == 0) {
            int damage = getPower() * 2;
            System.out.println(getName() + "の強攻撃！ " + target.getName() + "に" + damage + "のダメージ");
            target.damage(damage);
        } else {
            super.attack(target, turn);
        }
    }
}
