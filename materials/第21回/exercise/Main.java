package exercise;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int attack = random.nextInt(6) + 5;
        System.out.println("ダメージ=" + BattleUtil.addDamage(attack, 3));
    }
}

