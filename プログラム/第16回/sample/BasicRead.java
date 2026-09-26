public class BasicRead {
    public static void main(String[] args) {
        int hp = 20;
        int damage = 7;
        hp = hp - damage;
        if (hp <= 0) {
            System.out.println("戦闘不能");
        } else {
            System.out.println(hp);
        }
    }
}
