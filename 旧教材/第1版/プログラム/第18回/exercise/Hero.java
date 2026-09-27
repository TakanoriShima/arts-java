public class Hero extends Character {
    Hero(String name, int hp) {
        super(name, hp);
    }

    @Override
    void attack() {
        System.out.println(name + "の剣攻撃");
    }
}
