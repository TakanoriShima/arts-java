package sample;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int attack = random.nextInt(11) + 10;
        int damage = BattleUtil.addDamage(attack, 5);
        System.out.println("攻撃力=" + attack);
        System.out.println("ダメージ=" + damage);
    }
}
