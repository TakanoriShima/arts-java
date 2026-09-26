public class Character {
    private String name;
    private int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void damage(int amount) {
        hp = hp - amount;
        if (hp < 0) {
            hp = 0;
        }
    }

    int getHp() {
        return hp;
    }

    void showStatus() {
        System.out.println(name + " HP=" + hp);
    }
}
