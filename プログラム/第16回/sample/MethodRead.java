public class MethodRead {
    static int calculateDamage(int attack, int defense) {
        return attack - defense;
    }

    public static void main(String[] args) {
        int result = calculateDamage(12, 5);
        System.out.println(result);
    }
}
