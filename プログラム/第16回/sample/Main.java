public class Main {
    public static void main(String[] args) {
        // 勇者のステータス
        String heroName = "勇者";
        int heroHp = 30;
        int heroAttack = 8;

        // 敵のステータス
        String enemyName = "スライム";
        int enemyHp = 25;
        int enemyAttack = 6;

        // 今のターン数
        int turn = 1;

        System.out.println(enemyName + "があらわれた！");

        // どちらかの HP が 0 以下になるまで、戦闘を続ける
        while (heroHp > 0 && enemyHp > 0) {
            System.out.println();
            System.out.println("--- ターン" + turn + " ---");

            // 勇者の攻撃（3 ターンごとに会心の一撃で 2 倍）
            int damage = heroAttack;
            if (turn % 3 == 0) {
                damage = heroAttack * 2;
                System.out.println("会心の一撃！");
            }
            enemyHp -= damage;
            System.out.println(heroName + "の攻撃！ " + enemyName + "に" + damage + "のダメージ");

            // 敵を倒したら、敵は攻撃できないのでループを抜ける
            if (enemyHp <= 0) {
                break;
            }

            // 敵の攻撃
            heroHp -= enemyAttack;
            System.out.println(enemyName + "の攻撃！ " + heroName + "に" + enemyAttack + "のダメージ");

            System.out.println(heroName + " HP:" + heroHp + "  " + enemyName + " HP:" + enemyHp);
            turn++;
        }

        // 勝敗の表示
        System.out.println();
        if (enemyHp <= 0) {
            System.out.println(enemyName + "をたおした！");
        } else {
            System.out.println(heroName + "はたおれてしまった…");
        }
    }
}
