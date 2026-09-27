// 抽象クラス：Character そのものは new できない。必ず Hero や Enemy などの子クラスを作って使う
public abstract class Character {
    private final String name;
    private int hp;
    private final int maxHp;
    private int power;

    public Character(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getPower() {
        return power;
    }

    // 名前と HP を表示する
    public void showStatus() {
        System.out.println(name + " HP:" + hp + "/" + maxHp);
    }

    // 抽象メソッド：攻撃のしかたはキャラクターごとに違うので、子クラスで必ず書く
    public abstract void attack(Character target, int turn);

    // ふつうの攻撃（子クラスの attack の中から使える）
    public void normalAttack(Character target) {
        System.out.println(name + "の攻撃！ " + target.getName() + "に" + power + "のダメージ");
        target.damage(power);
    }

    // ダメージを受ける（HP は 0 より小さくならない）
    public void damage(int amount) {
        hp -= amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    // HP を回復する（最大 HP を超えない）
    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    // HP が残っていれば true
    public boolean isAlive() {
        return hp > 0;
    }

    // 攻撃力を amount だけ上げる（上限は 20）
    public void powerUp(int amount) {
        power += amount;
        if (power > 20) {
            power = 20;
        }
    }
}
