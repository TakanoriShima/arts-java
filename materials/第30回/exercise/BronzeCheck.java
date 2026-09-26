public class BronzeCheck {
    public static void main(String[] args) {
        int hp = 50;
        int damage = 18;
        hp = hp - damage;

        if (hp > 0) {
            System.out.println("生存");
        } else {
            System.out.println("戦闘不能");
        }

        for (int index = 0; index < 3; index++) {
            System.out.println(index);
        }
    }
}

