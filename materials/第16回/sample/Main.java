public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        hero.showStatus();
        hero.damage(30);
        hero.showStatus();
    }
}
