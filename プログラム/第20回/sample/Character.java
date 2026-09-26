public abstract class Character {
    protected String name;

    Character(String name) {
        this.name = name;
    }

    abstract void attack();
}
