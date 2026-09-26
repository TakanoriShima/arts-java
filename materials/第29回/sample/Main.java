public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        Character enemy = new Character("ドラゴン", 50);

        hero.attack(enemy, 25);
        enemy.showStatus();
        if (enemy.isAlive()) {
            System.out.println("戦闘は続く");
        } else {
            System.out.println("敵を倒した");
        }
    }
}
