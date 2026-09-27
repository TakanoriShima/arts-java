package battle;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

// 第29回：このバトルに、自分で考えた機能を追加する
// 追加した機能と、変更したファイル・メソッドを、ここにメモしておく
//   機能：
//   変更したところ：
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String[] commands = { "攻撃", "薬草を使う", "にげる", "ためる", "回復魔法" };

        Hero hero = new Hero("勇者", 30, 8, 2);
        Priest priest = new Priest("僧侶", 25, 4);

        // 味方パーティー
        ArrayList<Character> party = new ArrayList<>();
        party.add(hero);
        party.add(new Wizard("魔法使い", 15, 5));
        party.add(new Warrior("戦士", 40, 10));

        // 敵のグループ
        ArrayList<Enemy> enemies = new ArrayList<>();
        enemies.add(new Enemy("スライム", 20, 5, 5));
        enemies.add(new Enemy("ゴブリン", 40, 7, 12));
        enemies.add(new Enemy("オーク", 70, 9, 30));
        enemies.add(new HealSlime("いやしスライム", 25, 3, 20));

        int turn = 1;
        int totalExp = 0;
        boolean escaped = false;

        System.out.println("魔物の群れがあらわれた！");

        // ===== ターンのくり返し =====
        while (!party.isEmpty() && !enemies.isEmpty()) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");

            if (turn == 2) {
                party.add(priest);
                System.out.println(priest.getName() + "が仲間に加わった！");
            }

            showAll(party, enemies);

            // ----- 味方の行動 -----
            int command = inputNumber(scanner, "コマンドを選ぶ", commands);
            switch (command) {
                case 1:
                    Enemy target = chooseTarget(scanner, enemies);
                    for (Character member : party) {
                        if (enemies.isEmpty()) {
                            break;
                        }
                        // ねらった敵をたおしていたら、先頭の敵をねらう
                        if (!target.isAlive()) {
                            target = enemies.get(0);
                        }
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
                case 5:
                    // 回復できる（Healer の）仲間が、いちばん HP の少ない仲間を回復する
                    boolean healed = false;
                    for (Character member : party) {
                        if (member instanceof Healer) {
                            Healer healer = (Healer) member;
                            healer.cure(findWeakest(party));
                            healed = true;
                        }
                    }
                    if (!healed) {
                        System.out.println("回復魔法を使える仲間がいない！");
                    }
                    break;
            }
            // にげた、または敵が全滅したら、ここで終わる（turn を増やさない）
            if (escaped || enemies.isEmpty()) {
                break;
            }

            // ----- 敵の行動 -----
            enemyTurn(enemies, party, turn, random);
            if (party.isEmpty()) {
                break;
            }
            turn++;
        }

        // ===== 結果 =====
        System.out.println();
        if (escaped) {
            System.out.println("うまくにげきれた。");
        } else if (enemies.isEmpty()) {
            System.out.println(String.format("%dターンで勝利！ 経験値%dを手に入れた", turn, totalExp));
        } else {
            System.out.println(String.format("%dターン目にパーティーは全滅した…", turn));
        }
    }

    // 味方と敵の全員の状態を表示する（敵には番号を付ける）
    static void showAll(ArrayList<Character> party, ArrayList<Enemy> enemies) {
        for (Character member : party) {
            member.showStatus();
            if (member.getHp() <= member.getMaxHp() / 4) {
                System.out.println("  ↑ ピンチ！");
            }
        }
        System.out.println("敵：" + enemies.size() + "体");
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = enemies.get(i);
            System.out.println(String.format("  %d: %s HP:%d/%d", i + 1, enemy.getName(), enemy.getHp(), enemy.getMaxHp()));
        }
    }

    // HP がいちばん少ないメンバーを返す
    static Character findWeakest(ArrayList<Character> members) {
        Character weakest = members.get(0);
        for (Character member : members) {
            if (member.getHp() < weakest.getHp()) {
                weakest = member;
            }
        }
        return weakest;
    }

    // ねらう敵を番号で選ばせる
    static Enemy chooseTarget(Scanner scanner, ArrayList<Enemy> enemies) {
        String[] names = new String[enemies.size()];
        for (int i = 0; i < enemies.size(); i++) {
            names[i] = enemies.get(i).getName();
        }
        int number = inputNumber(scanner, "ねらう敵を選ぶ", names);
        return enemies.get(number - 1);
    }

    // 残っている敵が、1 体ずつランダムに相手を選んで攻撃する
    static void enemyTurn(ArrayList<Enemy> enemies, ArrayList<Character> party, int turn, Random random) {
        for (Enemy enemy : enemies) {
            if (party.isEmpty()) {
                break;
            }
            // 回復できる敵は、弱っている敵がいれば、攻撃の代わりに回復する
            Enemy weakest = findWeakestEnemy(enemies);
            if (enemy instanceof Healer && weakest.getHp() < weakest.getMaxHp() / 2) {
                Healer healer = (Healer) enemy;
                healer.cure(weakest);
            } else {
                Character target = party.get(random.nextInt(party.size()));
                enemy.attack(target, turn);
                if (!target.isAlive()) {
                    System.out.println(target.getName() + "はたおれた…");
                    party.remove(target);
                }
            }
        }
    }

    // HP がいちばん少ない敵を返す（演習２-２で使う）
    static Enemy findWeakestEnemy(ArrayList<Enemy> enemies) {
        Enemy weakest = enemies.get(0);
        for (Enemy enemy : enemies) {
            if (enemy.getHp() < weakest.getHp()) {
                weakest = enemy;
            }
        }
        return weakest;
    }

    // 選択肢を表示し、1〜選択肢の数 の番号が入力されるまで聞いて、その番号を返す
    static int inputNumber(Scanner scanner, String message, String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println((i + 1) + ": " + choices[i]);
        }
        int number = 0;
        do {
            System.out.print(message + " > ");
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("数字を入力してください");
                scanner.next();
            }
        } while (number < 1 || number > choices.length);
        return number;
    }
}
