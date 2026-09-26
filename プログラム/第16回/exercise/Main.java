public class Main {
    public static void main(String[] args) {
        Character enemy = new Character("スライム", 80);
        enemy.showStatus();
        enemy.damage(20);
        enemy.showStatus();
    }
}

