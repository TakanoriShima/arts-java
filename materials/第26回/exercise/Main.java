public class Main {
    public static void main(String[] args) {
        Character hero = new Hero("戦士", 70);
        Character enemy = new Enemy("ゴブリン", 40);

        hero.attack(enemy);
        if (enemy.isAlive()) {
            enemy.attack(hero);
        }

        hero.showStatus();
        enemy.showStatus();
    }
}

