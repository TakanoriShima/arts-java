public class Main {
    public static void main(String[] args) {
        Character hero = new Character("僧侶", 90);
        Item potion = new Item("大きなPotion", 45);

        hero.damage(60);
        potion.use(hero);
        hero.showStatus();
    }
}

