package roguelike.effect;

public abstract class AbstractEffect implements Effect {
    private final int amount;

    public AbstractEffect(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
