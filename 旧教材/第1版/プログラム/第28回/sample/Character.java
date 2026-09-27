public class Character {
    private String name;
    private int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void attack(Character target, int damage) {
        System.out.println(name + "の攻撃");
        target.damage(damage);
    }

    void damage(int amount) {
        hp = hp - amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    void showStatus() {
        System.out.println(name + " HP=" + hp);
    }
}
