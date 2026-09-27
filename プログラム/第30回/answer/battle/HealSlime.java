package battle;

// いやしスライム：Enemy を受けつぎ、Healer（回復できる）の約束も守る
public class HealSlime extends Enemy implements Healer {
    public HealSlime(String name, int hp, int power, int exp) {
        super(name, hp, power, exp);
    }

    @Override
    public void cure(Character target) {
        target.heal(10);
        System.out.println(getName() + "はなかまを回復した！ " + target.getName() + "のHPが" + target.getHp() + "になった");
    }
}
