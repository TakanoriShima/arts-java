public class Main {
    public static void main(String[] args) {
        Character[] party = {
            new Hero("戦士"),
            new Enemy("ゴブリン")
        };

        for (Character character : party) {
            character.attack();
        }
    }
}

