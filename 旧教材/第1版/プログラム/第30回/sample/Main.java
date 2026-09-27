public class Main {
    public static void main(String[] args) {
        int hp = 100;
        int damage = 35;

        if (damage < hp) {
            hp = hp - damage;
        }

        for (int turn = 1; turn <= 2; turn++) {
            System.out.println("turn=" + turn + " HP=" + hp);
        }
    }
}
