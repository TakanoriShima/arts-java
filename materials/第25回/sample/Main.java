public class Main {
    public static void main(String[] args) {
        String name = "勇者";
        int hp = 75;
        String message = String.format("%s のHPは %d", name, hp);

        System.out.println(message);
        System.out.println("名前の長さ=" + name.length());
        System.out.println("勇を含む=" + name.contains("勇"));
        System.out.println("大文字表示=" + "attack".toUpperCase());
    }
}
