public class Hero extends Character {
    Hero(String name, int hp) {
        super(name, hp);
    }

    @Override
    void attack(Character target) {
        int damage = 12;
        System.out.println(name + "の攻撃");
        target.damage(damage);
        System.out.println(damage + "ダメージ");
    }
}
