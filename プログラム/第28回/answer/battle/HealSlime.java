package battle;

// いやしスライム：Enemy を受けつぎ、Healer（回復できる）の約束も守る
public class HealSlime extends Enemy implements Healer {
    private int cureCount = 3;   // 回復できる残りの回数

    public HealSlime(String name, int hp, int power, int exp) {
        super(name, hp, power, exp);
    }

    // 回復は 3 回まで（回数の制限がないと、バトルが終わらなくなることがある）
    @Override
    public void cure(Character target) {
        if (cureCount > 0) {
            target.heal(10);
            cureCount--;
            System.out.println(getName() + "はなかまを回復した！ " + target.getName() + "のHPが" + target.getHp() + "になった");
        } else {
            System.out.println(getName() + "は回復しようとしたが、力が残っていない！");
        }
    }
}
