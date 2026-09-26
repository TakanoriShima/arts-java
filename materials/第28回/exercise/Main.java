public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        Character enemy = new Character("スライム", 50);

        // ここに自分の機能追加を書く。
        hero.attack(enemy, 15);
        enemy.showStatus();
    }
}

