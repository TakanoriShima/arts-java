public class Main {
    public static void main(String[] args) {
        Character hero = new Character("勇者", 100);
        hero.damage(40);
        hero.showStatus();
        System.out.println("HP=" + hero.getHp());
    }
}

