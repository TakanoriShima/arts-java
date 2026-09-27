import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // キーボードから入力するための道具
        Scanner scanner = new Scanner(System.in);

        // コマンドの一覧（配列）
        String[] commands = { "攻撃", "薬草を使う", "にげる", "ぼうぎょ" };

        // 勇者のステータス
        String heroName = "勇者";
        int heroHp = 30;
        int heroMaxHp = 30;
        int heroAttack = 8;
        int herbCount = 2;

        // 敵のステータス
        String enemyName = "ゴブリン";
        int enemyHp = 40;
        int enemyAttack = 7;

        int turn = 1;
        boolean escaped = false;

        System.out.println(enemyName + "があらわれた！");

        while (heroHp > 0 && enemyHp > 0) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");
            System.out.println(heroName + " HP:" + heroHp + "/" + heroMaxHp + "  薬草:" + herbCount + "個");
            System.out.println(enemyName + " HP:" + enemyHp);

            // コマンドの一覧を表示する
            for (int i = 0; i < commands.length; i++) {
                System.out.println((i + 1) + ": " + commands[i]);
            }

            // 1〜コマンドの数 の番号が入力されるまで、くり返し聞く
            int command;
            do {
                System.out.print("コマンドを選ぶ > ");
                command = scanner.nextInt();
            } while (command < 1 || command > commands.length);

            // ぼうぎょしたかどうか（ターンごとに false から始める）
            boolean defending = false;

            // 選んだコマンドごとの処理
            switch (command) {
                case 1:
                    int damage = heroAttack;
                    if (turn % 3 == 0) {
                        damage = heroAttack * 2;
                        System.out.println("会心の一撃！");
                    }
                    enemyHp -= damage;
                    System.out.println(heroName + "の攻撃！ " + enemyName + "に" + damage + "のダメージ");
                    break;
                case 2:
                    if (herbCount > 0) {
                        heroHp += 15;
                        if (heroHp > heroMaxHp) {
                            heroHp = heroMaxHp;
                        }
                        herbCount--;
                        System.out.println(heroName + "は薬草を使った！ HPが" + heroHp + "になった");
                    } else {
                        System.out.println("薬草がない！");
                    }
                    break;
                case 3:
                    System.out.println(heroName + "はにげだした！");
                    escaped = true;
                    break;
                case 4:
                    System.out.println(heroName + "は身を守っている");
                    defending = true;
                    break;
            }

            // にげた、または敵をたおしたら、ループを抜ける
            if (escaped || enemyHp <= 0) {
                break;
            }

            // 敵の攻撃（ぼうぎょしたターンは半分）
            int enemyDamage = enemyAttack;
            if (defending) {
                enemyDamage = enemyAttack / 2;
            }
            heroHp -= enemyDamage;
            System.out.println(enemyName + "の攻撃！ " + heroName + "に" + enemyDamage + "のダメージ");
            turn++;
        }

        // 結果の表示
        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (enemyHp <= 0) {
            System.out.println(enemyName + "をたおした！");
        } else {
            System.out.println(heroName + "はたおれてしまった…");
        }
    }
}
