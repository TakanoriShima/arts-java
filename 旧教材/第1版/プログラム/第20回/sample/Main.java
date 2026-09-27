public class Main {
    public static void main(String[] args) {
        Character[] characters = {
            new Hero("勇者"),
            new Enemy("スライム")
        };

        for (Character character : characters) {
            character.attack();
        }
    }
}
