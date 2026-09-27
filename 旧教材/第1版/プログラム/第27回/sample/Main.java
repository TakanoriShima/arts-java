public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        Item potion = new Item("Potion", 30);

        hero.damage(70);
        hero.showStatus();
        potion.use(hero);
        hero.showStatus();
    }
}
