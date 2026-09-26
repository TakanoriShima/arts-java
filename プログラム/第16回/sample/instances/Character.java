public class Character {
    private int hp;

    public Character(int hp) {
        this.hp = hp;
    }

    public void takeDamage(int damage) {
        hp = hp - damage;
        if (hp < 0) {
            hp = 0;
        }
    }

    public int getHp() {
        return hp;
    }
}
