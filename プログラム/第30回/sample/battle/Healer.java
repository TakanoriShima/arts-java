package battle;

// インタフェース：「回復できる」という能力の約束
// implements したクラスは、cure メソッドを必ず書く
public interface Healer {
    void cure(Character target);
}
