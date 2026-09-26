public class ConstructorCheck {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 30);
        System.out.println(hero.getName() + " HP:" + hero.getHp());
    }
}
