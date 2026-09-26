public class Main {
    public static void main(String[] args) {
        Character[] party = {
            new Character("勇者", 100),
            new Character("魔法使い", 70),
            new Character("僧侶", 80)
        };

        for (Character character : party) {
            character.damage(15);
            character.showStatus();
        }
    }
}
