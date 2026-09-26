public class Character {
    protected String name;
    protected int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void attack() {
        System.out.println(name + "の攻撃");
    }
}
