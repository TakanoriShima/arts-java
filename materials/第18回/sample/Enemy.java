public class Enemy extends Character {
    Enemy(String name, int hp) {
        super(name, hp);
    }

    @Override
    void attack() {
        System.out.println(name + "の体当たり");
    }
}
