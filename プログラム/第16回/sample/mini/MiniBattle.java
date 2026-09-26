public class MiniBattle {
    public static void main(String[] args) {
        Character enemy = new Character("スライム", 12);
        enemy.takeDamage(5);
        System.out.println(enemy.getName() + "のHP: " + enemy.getHp());
        if (enemy.getHp() == 0) {
            System.out.println("戦闘不能");
        } else {
            System.out.println("まだ戦える");
        }
    }
}
