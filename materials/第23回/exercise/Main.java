import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> party = new ArrayList<>();
        party.add(new Character("戦士"));
        party.add(new Character("盗賊"));
        party.add(new Character("僧侶"));

        party.remove(0);
        for (Character character : party) {
            character.showName();
        }
        System.out.println("残り=" + party.size());
    }
}

