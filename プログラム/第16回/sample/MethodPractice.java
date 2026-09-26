public class MethodPractice {
    static int remainingHp(int hp, int damage) {
        int result = hp - damage;
        if (result < 0) {
            return 0;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(remainingHp(20, 7));
        System.out.println(remainingHp(5, 8));
        System.out.println(remainingHp(5, 5));

        int hp = 20;
        hp = remainingHp(hp, 7);
        System.out.println(hp);
    }
}
