public class Character {
    // フィールド（キャラクターが持つ情報）
    String name;
    int hp;
    int maxHp;
    int power;

    // コンストラクタ（new したときに最初に実行される）
    Character(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
    }

    // 名前と HP を表示する
    void showStatus() {
        System.out.println(name + " HP:" + hp + "/" + maxHp);
    }

    // target に攻撃する（3 ターンごとに会心の一撃で 1.5 倍）
    void attack(Character target, int turn) {
        int damage = power;
        if (turn % 3 == 0) {
            System.out.println("会心の一撃！");
            damage = (int) (power * 1.5);
        }
        System.out.println(name + "の攻撃！ " + target.name + "に" + damage + "のダメージ");
        target.hp -= damage;
    }

    // HP を回復する（最大 HP を超えない）
    void heal(int amount) {
        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    // HP が残っていれば true
    boolean isAlive() {
        return hp > 0;
    }

    // 攻撃力を amount だけ上げる
    void powerUp(int amount) {
        power += amount;
    }
}
