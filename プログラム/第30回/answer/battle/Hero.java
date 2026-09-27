package battle;

// 勇者：Character の機能をすべて受けつぎ、薬草と会心の一撃を持つ
public class Hero extends Character {
    // 定数：すべての勇者で共通の、変わらない値（static final）
    public static final int HERB_POWER = 15;

    private int herbCount;

    public Hero(String name, int hp, int power, int herbCount) {
        super(name, hp, power);   // 親クラス（Character）のコンストラクタを呼ぶ
        this.herbCount = herbCount;
    }

    public int getHerbCount() {
        return herbCount;
    }

    // 薬草を使う
    public void useHerb() {
        if (herbCount > 0) {
            heal(HERB_POWER);
            herbCount--;
            System.out.println(getName() + "は薬草を使った！ HPが" + getHp() + "/" + getMaxHp() + "になった");
        } else {
            System.out.println("薬草がない！");
        }
    }

    // 勇者の攻撃：3 ターンごと、または HP が半分以下のときに会心の一撃（1.5 倍）
    @Override
    public void attack(Character target, int turn) {
        if (turn % 3 == 0 || getHp() <= getMaxHp() / 2) {
            int damage = (int) (getPower() * 1.5);
            System.out.println("会心の一撃！");
            System.out.println(getName() + "の攻撃！ " + target.getName() + "に" + damage + "のダメージ");
            target.damage(damage);
        } else {
            normalAttack(target);   // Character のふつうの攻撃
        }
    }
}
