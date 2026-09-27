import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // TODO 演習２-２：4 番目のコマンド「ためる」を追加する
        String[] commands = { "攻撃", "薬草を使う", "にげる" };

        // キャラクターを作る（インスタンスの生成）
        Character hero = new Character("勇者", 30, 8);
        Character enemy = new Character("ゴブリン", 40, 7);
        int herbCount = 2;

        int turn = 1;
        boolean escaped = false;

        System.out.println(enemy.name + "があらわれた！");

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
                    if (herbCount > 0) {
                        hero.heal(15);
                        herbCount--;
                        System.out.println(hero.name + "は薬草を使った！ HPが" + hero.hp + "になった");
                    } else {
                        System.out.println("薬草がない！");
                    }
                    break;
                case 3:
                    System.out.println(hero.name + "はにげだした！");
                    escaped = true;
                    break;
                // TODO 演習２-２：case 4（ためる）を追加する
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
            System.out.println(enemy.name + "をたおした！");
        } else {
            System.out.println(hero.name + "はたおれてしまった…");
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
