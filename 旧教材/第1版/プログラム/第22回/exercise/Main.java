public class Main {
    public static void main(String[] args) {
        Character[] party = {
            new Character("戦士", 90),
            new Character("盗賊", 75),
            new Character("僧侶", 65)
        };

        for (Character character : party) {
            character.damage(20);
            character.showStatus();
        }
    }
}

