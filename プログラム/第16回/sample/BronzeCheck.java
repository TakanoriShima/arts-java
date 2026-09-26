public class BronzeCheck {
    static int calculateDamage(int attack, int defense) {
        return attack - defense;
    }

    public static void main(String[] args) {
        int damage = calculateDamage(9, 4);
        System.out.println(damage + 2);
    }
}
