public class Item {
    private String name;
    private int healPower;

    Item(String name, int healPower) {
        this.name = name;
        this.healPower = healPower;
    }

    void use(Character target) {
        System.out.println(name + "を使った");
        target.heal(healPower);
    }
}
