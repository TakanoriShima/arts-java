package battle;

// 僧侶：Character を受けつぎ、Healer（回復できる）の約束も守る
public class Priest extends Character implements Healer {
    public Priest(String name, int hp, int power) {
        super(name, hp, power);
    }

    @Override
    public void attack(Character target, int turn) {
        System.out.println(getName() + "は杖でたたいた！ " + target.getName() + "に" + getPower() + "のダメージ");
        target.damage(getPower());
    }

    // Healer の約束：回復の祈り（12 回復）
    @Override
    public void cure(Character target) {
        target.heal(12);
        System.out.println(getName() + "は回復の祈りをささげた！ " + target.getName() + "のHPが" + target.getHp() + "になった");
    }
}
