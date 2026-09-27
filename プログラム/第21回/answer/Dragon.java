// ドラゴン：Enemy を受けつぎ、奇数ターンに炎をはく
public class Dragon extends Enemy {
    public Dragon(String name, int hp, int power, int exp) {
        super(name, hp, power, exp);
    }

    @Override
    public void attack(Character target, int turn) {
        if (turn % 2 == 1) {
            int damage = getPower() + 5;
            System.out.println(getName() + "は炎をはいた！ " + target.getName() + "に" + damage + "のダメージ");
            target.damage(damage);
        } else {
            super.attack(target, turn);   // Enemy の攻撃（4 ターンごとの強攻撃を含む）
        }
    }
}
