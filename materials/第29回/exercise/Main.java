public class Main {
    public static void main(String[] args) {
        Character hero = new Character("自分のキャラクター", 100);
        Character enemy = new Character("自分で決めた敵", 60);

        hero.attack(enemy, 20);
        enemy.showStatus();
        // ここから自分の機能追加を続ける。
    }
}

