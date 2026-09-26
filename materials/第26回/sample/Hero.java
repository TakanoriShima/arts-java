import java.util.Random;

public class Hero extends Character {
    private Random random = new Random();

    Hero(String name, int hp) {
        super(name, hp);
    }

    @Override
    void attack(Character target) {
        int damage = random.nextInt(11) + 10;
        System.out.println(name + "の攻撃");
        target.damage(damage);
        System.out.println(damage + "ダメージ");
    }
}
