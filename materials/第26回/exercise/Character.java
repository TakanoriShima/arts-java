public abstract class Character {
    protected String name;
    protected int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    abstract void attack(Character target);

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
