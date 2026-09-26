public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        hero.damage(35);
        hero.showStatus();
        System.out.println("確認したHP=" + hero.getHp());
    }
}
