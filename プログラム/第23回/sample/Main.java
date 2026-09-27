import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] commands = { "攻撃", "薬草を使う", "にげる", "ためる" };

        Hero hero = new Hero("勇者", 30, 8, 2);
        Wizard wizard = new Wizard("魔法使い", 20, 5);
        Warrior warrior = new Warrior("戦士", 40, 10);
        Enemy enemy = new Enemy("トロル", 110, 12, 60);

        // 味方パーティー：Hero も Wizard も Warrior も Character 型の配列に入れられる
        Character[] party = { hero, wizard, warrior };

        int turn = 1;
        boolean escaped = false;

        System.out.println(enemy.getName() + "があらわれた！");

        while (isPartyAlive(party) && enemy.isAlive()) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");
            for (Character member : party) {
                member.showStatus();
            }
            enemy.showStatus();

            int command = inputCommand(scanner, commands);

            switch (command) {
                case 1:
                    // 全員が攻撃する（誰の attack が呼ばれるかは、中身のクラスで決まる）
                    for (Character member : party) {
                        if (member.isAlive() && enemy.isAlive()) {
                            member.attack(enemy, turn);
                        }
                    }
                    break;
                case 2:
                    hero.useHerb();
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

            if (escaped || !enemy.isAlive()) {
                break;
            }

            enemy.attack(findTarget(party, turn), turn);
            turn++;
        }

        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (!enemy.isAlive()) {
            System.out.println(enemy.getName() + "をたおした！ 経験値" + enemy.getExp() + "を手に入れた");
        } else {
            System.out.println("パーティーは全滅した…");
        }
    }

    // パーティーに生きているメンバーが 1 人でもいれば true
    static boolean isPartyAlive(Character[] party) {
        for (Character member : party) {
            if (member.isAlive()) {
                return true;
            }
        }
        return false;
    }

    // 敵がねらうメンバーを決める（ターンごとに順番に。たおれているメンバーは飛ばす）
    static Character findTarget(Character[] party, int turn) {
        for (int i = 0; i < party.length; i++) {
            Character member = party[(turn + i) % party.length];
            if (member.isAlive()) {
                return member;
            }
        }
        return party[0];
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
