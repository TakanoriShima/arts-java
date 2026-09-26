public class Main {
    public static void main(String[] args) {
        Hero hero = new Hero("勇者", 100);
        Enemy enemy = new Enemy("スライム", 50);
        hero.attack();
        enemy.attack();
    }
}
