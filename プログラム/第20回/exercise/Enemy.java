public class Enemy extends Character {
    Enemy(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + "は魔法で攻撃");
    }
}
