// 魔法使い：HP は低いが、魔法で大きなダメージを与える
public class Wizard extends Character {
    public Wizard(String name, int hp, int power) {
        super(name, hp, power);
    }

    // 魔法使いの攻撃：攻撃力の 2 倍の火の魔法
    @Override
    public void attack(Character target, int turn) {
        int damage = getPower() * 2;
        System.out.println(getName() + "は火の魔法をとなえた！ " + target.getName() + "に" + damage + "のダメージ");
        target.damage(damage);
    }
}
