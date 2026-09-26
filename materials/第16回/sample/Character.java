public class Character {
    String name;
    int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void damage(int amount) {
        hp = hp - amount;
    }

    void showStatus() {
        System.out.println(name + " HP=" + hp);
    }
}
