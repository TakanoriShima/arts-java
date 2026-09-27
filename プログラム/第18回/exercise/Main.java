import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commands = { "攻撃", "薬草を使う", "にげる" };

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

        // TODO 演習２-２：isAlive メソッドを使った条件に書きかえる
        while (heroHp > 0 && enemyHp > 0) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");
            showStatus(heroName, heroHp, heroMaxHp);
            showStatus(enemyName, enemyHp);

            int command = inputCommand(scanner, commands);

            switch (command) {
                case 1:
                    int damage = calcDamage(heroAttack, turn);
                    enemyHp -= damage;
                    System.out.println(heroName + "の攻撃！ " + enemyName + "に" + damage + "のダメージ");
                    break;
                case 2:
                    if (herbCount > 0) {
                        heroHp = heal(heroHp, heroMaxHp, 15);
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
            }

            if (escaped || enemyHp <= 0) {
                break;
            }

            heroHp -= enemyAttack;
            System.out.println(enemyName + "の攻撃！ " + heroName + "に" + enemyAttack + "のダメージ");
            turn++;
        }

        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (enemyHp <= 0) {
            System.out.println(enemyName + "をたおした！");
        } else {
            System.out.println(heroName + "はたおれてしまった…");
        }
    }

    // 名前と HP を表示する（最大 HP あり）
    static void showStatus(String name, int hp, int maxHp) {
        System.out.println(name + " HP:" + hp + "/" + maxHp);
        // TODO 演習２-３：showHpBar を呼び出して、HP バーも表示する
    }

    // 名前と HP を表示する（最大 HP なし）
    static void showStatus(String name, int hp) {
        System.out.println(name + " HP:" + hp);
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

    // 攻撃力とターン数から、ダメージを計算して返す（3 ターンごとに 1.5 倍）
    static int calcDamage(int attack, int turn) {
        if (turn % 3 == 0) {
            System.out.println("会心の一撃！");
            return (int) (attack * 1.5);
        }
        return attack;
    }

    // 回復した後の HP を返す（最大 HP を超えない）
    static int heal(int hp, int maxHp, int amount) {
        // TODO 演習２-１：hp に amount を足し、maxHp を超えたら maxHp にしてから返す
        return hp;
    }

    // TODO 演習２-２：HP が 0 より大きければ true を返す isAlive メソッドを作る

    // TODO 演習２-３：HP 5 につき「■」を 1 個表示する showHpBar メソッドを作る
}
