public class Hero extends Character implements SkillUser {
    Hero(String name) {
        super(name);
    }

    @Override
    public void attack() {
        System.out.println(name + "の攻撃");
    }

    @Override
    public void useSkill() {
        System.out.println(name + "は炎の技を使った");
    }
}
