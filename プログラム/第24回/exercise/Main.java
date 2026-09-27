import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commands = { "攻撃", "薬草を使う", "にげる", "ためる" };

        Hero hero = new Hero("勇者", 30, 8, 2);
        Priest priest = new Priest("僧侶", 25, 4);

        // 味方パーティー（人数が変わるので ArrayList を使う）
        ArrayList<Character> party = new ArrayList<>();
        party.add(hero);
        party.add(new Wizard("魔法使い", 15, 5));
        party.add(new Warrior("戦士", 40, 10));

        // 敵のグループ
        ArrayList<Enemy> enemies = new ArrayList<>();
        // TODO 演習２-１：スライム（HP 20、攻撃力 5、経験値 5）、ゴブリン（40、7、12）、
        //               オーク（70、9、30）の 3 体を、この順に enemies に追加する

        int turn = 1;
        int totalExp = 0;
        boolean escaped = false;

        System.out.println("魔物の群れがあらわれた！");

        while (!party.isEmpty() && !enemies.isEmpty()) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");

            // 2 ターン目に、僧侶が仲間に加わる
            if (turn == 2) {
                party.add(priest);
                System.out.println(priest.getName() + "が仲間に加わった！");
            }

            // TODO 演習２-３：4 ターン目に、敵のグループの最後にスライム（20、5、5）を追加し、
            //               「スライムがあらわれた！」と表示する

            for (Character member : party) {
                member.showStatus();
            }
            System.out.println("敵：" + enemies.size() + "体");
            for (Enemy enemy : enemies) {
                enemy.showStatus();
            }

            int command = inputCommand(scanner, commands);

            switch (command) {
                case 1:
                    // 全員が、先頭の敵を攻撃する
                    for (Character member : party) {
                        if (enemies.isEmpty()) {
                            break;
                        }
                        Enemy target = enemies.get(0);
                        member.attack(target, turn);
                        if (!target.isAlive()) {
                            System.out.println(target.getName() + "をたおした！");
                            totalExp += target.getExp();
                            enemies.remove(target);
                        }
                    }
                    break;
                case 2:
                    if (hero.isAlive()) {
                        hero.useHerb();
                    } else {
                        System.out.println(hero.getName() + "はたおれていて薬草を使えない！");
                    }
                    break;
                case 3:
                    System.out.println("パーティーはにげだした！");
                    escaped = true;
                    break;
                case 4:
                    hero.powerUp(3);
                    System.out.println(hero.getName() + "は力をためた！ 攻撃力が" + hero.getPower() + "になった");
                    break;
            }

            if (escaped) {
                break;
            }

            // 残っている敵が、1 体ずつ攻撃する
            for (Enemy enemy : enemies) {
                if (party.isEmpty()) {
                    break;
                }
                Character target = party.get(turn % party.size());
                enemy.attack(target, turn);
                // TODO 演習２-２：target がたおれたら「〇〇はたおれた…」と表示し、party から取り除く
            }
            turn++;
        }

        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (enemies.isEmpty()) {
            System.out.println("魔物の群れをたおした！ 経験値" + totalExp + "を手に入れた");
        } else {
            System.out.println("パーティーは全滅した…");
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
