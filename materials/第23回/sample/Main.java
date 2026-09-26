import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> party = new ArrayList<>();
        party.add(new Character("勇者"));
        party.add(new Character("魔法使い"));
        party.add(new Character("僧侶"));

        for (Character character : party) {
            character.showName();
        }

        party.remove(1);
        System.out.println("残り=" + party.size());
    }
}
