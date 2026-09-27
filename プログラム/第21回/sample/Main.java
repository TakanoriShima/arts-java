import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commands = { "攻撃", "薬草を使う", "にげる", "ためる" };

        Hero hero = new Hero("勇者", 30, 8, 2);
        Enemy enemy = new Enemy("ゴブリン", 40, 7, 12);

        int turn = 1;
        boolean escaped = false;

        System.out.println(enemy.getName() + "があらわれた！");

        while (hero.isAlive() && enemy.isAlive()) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");
            hero.showStatus();
            enemy.showStatus();

            int command = inputCommand(scanner, commands);

            switch (command) {
                case 1:
                    hero.attack(enemy, turn);
                    break;
                case 2:
                    hero.useHerb();
                    break;
                case 3:
                    System.out.println(hero.getName() + "はにげだした！");
                    escaped = true;
                    break;
                case 4:
                    hero.powerUp(3);
                    System.out.println(hero.getName() + "は力をためた！ 攻撃力が" + hero.getPower() + "になった");
                    break;
            }

            if (escaped || !enemy.isAlive()) {
                break;
            }

            enemy.attack(hero, turn);
            turn++;
        }

        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + "をたおした！ 経験値" + enemy.getExp() + "を手に入れた");
        } else {
            System.out.println(hero.getName() + "はたおれてしまった…");
        }
    }

    // コマンドの一覧を表示し、正しい番号が入力されるまで聞いて、その番号を返す
    static int inputCommand(Scanner scanner, String[] commands) {
        for (int i = 0; i < commands.length; i++) {
            System.out.println((i + 1) + ": " + commands[i]);
        }
        int command;
        do {
            System.out.print("コマンドを選ぶ > ");
            command = scanner.nextInt();
        } while (command < 1 || command > commands.length);
        return command;
    }
}
