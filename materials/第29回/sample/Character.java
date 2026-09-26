public class Character {
    private String name;
    private int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void attack(Character target, int damage) {
        target.damage(damage);
        System.out.println(name + "は" + target.name + "に" + damage + "ダメージ");
    }

    void damage(int amount) {
        hp = hp - amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    boolean isAlive() {
        return hp > 0;
    }

    void showStatus() {
        System.out.println(name + " HP=" + hp);
    }
}
