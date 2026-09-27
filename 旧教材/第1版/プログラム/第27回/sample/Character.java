public class Character {
    private String name;
    private int hp;
    private int maxHp;

    Character(String name, int maxHp) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
    }

    void damage(int amount) {
        hp = hp - amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    void heal(int amount) {
        hp = hp + amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }

    void showStatus() {
        System.out.println(name + " HP=" + hp + "/" + maxHp);
    }
}
