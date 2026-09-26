public class Main {
    public static void main(String[] args) {
        String name = "魔法使い";
        int damage = 28;
        String log = String.format("%s は %d ダメージを与えた", name, damage);

        System.out.println(log);
        System.out.println("名前の長さ=" + name.length());
        System.out.println("魔を含む=" + name.contains("魔"));
    }
}

