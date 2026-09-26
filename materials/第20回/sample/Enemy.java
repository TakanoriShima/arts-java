public class Enemy extends Character {
    Enemy(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + "は体当たり");
    }
}
