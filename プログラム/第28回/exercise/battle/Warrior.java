package battle;

// 戦士：HP が高く、おので攻撃する
public class Warrior extends Character {
    public Warrior(String name, int hp, int power) {
        super(name, hp, power);
    }

    @Override
    public void attack(Character target, int turn) {
        System.out.println(getName() + "のおのの一撃！ " + target.getName() + "に" + getPower() + "のダメージ");
        target.damage(getPower());
    }

    // TODO 演習２-３：damage をオーバーライドし、受けるダメージを 2 へらす（ただし最低 1 は受ける）
}
