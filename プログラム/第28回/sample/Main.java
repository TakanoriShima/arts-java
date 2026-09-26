public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        Character enemy = new Character("ゴブリン", 45);

        hero.attack(enemy, 20);
        enemy.showStatus();
    }
}
