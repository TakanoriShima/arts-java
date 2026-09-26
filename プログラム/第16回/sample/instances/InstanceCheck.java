public class InstanceCheck {
    public static void main(String[] args) {
        Character hero = new Character(20);
        Character enemy = new Character(10);
        hero.takeDamage(5);
        System.out.println(hero.getHp());
        System.out.println(enemy.getHp());
    }
}
