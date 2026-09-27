package battle;

// 僧侶：攻撃は弱いが、打たれ強い（回復の力は第28回で追加する）
public class Priest extends Character {
    public Priest(String name, int hp, int power) {
        super(name, hp, power);
    }

    @Override
    public void attack(Character target, int turn) {
        System.out.println(getName() + "は杖でたたいた！ " + target.getName() + "に" + getPower() + "のダメージ");
        target.damage(getPower());
    }
}
