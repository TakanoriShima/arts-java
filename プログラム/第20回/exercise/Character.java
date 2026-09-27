public class Character {
    // フィールドは private にして、クラスの外から直接変更できないようにする
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

    // getter：フィールドの値を外から読むためのメソッド
    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getPower() {
        return power;
    }

    // TODO 演習２-２：最大 HP を返す getMaxHp メソッドを作る

    // 名前と HP を表示する
    public void showStatus() {
        System.out.println(name + " HP:" + hp + "/" + maxHp);
    }

    // target に攻撃する（3 ターンごとに会心の一撃で 1.5 倍）
    public void attack(Character target, int turn) {
        int damage = power;
        if (turn % 3 == 0) {
            System.out.println("会心の一撃！");
            damage = (int) (power * 1.5);
        }
        System.out.println(name + "の攻撃！ " + target.getName() + "に" + damage + "のダメージ");
        target.damage(damage);
    }

    // ダメージを受ける
    public void damage(int amount) {
        hp -= amount;
        // TODO 演習２-１：HP が 0 より小さくなったら 0 にする
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

    // 攻撃力を amount だけ上げる
    public void powerUp(int amount) {
        power += amount;
        // TODO 演習２-３：攻撃力の上限を 20 にする
    }
}
