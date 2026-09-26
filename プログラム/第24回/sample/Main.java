import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Ether");

        // System.out.println(items.get(2)); // 添字を修正して確認する
        System.out.println(items.get(1));

        int[] damage = { 10, 20, 30 };
        System.out.println(damage[2]);
    }
}
