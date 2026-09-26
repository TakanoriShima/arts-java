public class Hero extends Character {
    Hero(String name) {
        super(name);
    }

    @Override
    void attack() {
        System.out.println(name + "は剣で攻撃");
    }
}
