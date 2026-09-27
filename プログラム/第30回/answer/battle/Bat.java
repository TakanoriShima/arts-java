package battle;

// こうもり：Enemy を受けつぎ、1 ターンに 2 回攻撃する
public class Bat extends Enemy {
    public Bat(String name, int hp, int power, int exp) {
        super(name, hp, power, exp);
    }

    @Override
    public void attack(Character target, int turn) {
        normalAttack(target);
        normalAttack(target);
    }
}
