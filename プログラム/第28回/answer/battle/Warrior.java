package battle;

// 戦士：HP が高く、おので攻撃する。よろいでダメージを少しへらす
public class Warrior extends Character {
    public Warrior(String name, int hp, int power) {
        super(name, hp, power);
    }

    @Override
    public void attack(Character target, int turn) {
        System.out.println(getName() + "のおのの一撃！ " + target.getName() + "に" + getPower() + "のダメージ");
        target.damage(getPower());
    }

    // よろいで、受けるダメージを 2 へらす（最低 1 は受ける）
    @Override
    public void damage(int amount) {
        int reduced = Math.max(amount - 2, 1);
        System.out.println(getName() + "はよろいで受け止めた！ ダメージは" + reduced + "になった");
        super.damage(reduced);
    }
}
