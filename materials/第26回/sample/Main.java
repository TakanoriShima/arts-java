import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Character hero = new Hero("勇者", 60);
        Character enemy = new Enemy("スライム", 45);
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(hero);
        characters.add(enemy);

        hero.attack(enemy);
        if (enemy.isAlive()) {
            enemy.attack(hero);
        }

        for (Character character : characters) {
            character.showStatus();
        }
    }
}
