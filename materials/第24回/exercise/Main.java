import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>();
        items.add("Potion");
        items.add("Bomb");

        // まず、次の行のコメントを外してエラーを確認する。
        // System.out.println(items.get(2));

        int[] damage = { 12, 24, 36 };
        System.out.println("修正後のダメージ=" + damage[1]);
    }
}

