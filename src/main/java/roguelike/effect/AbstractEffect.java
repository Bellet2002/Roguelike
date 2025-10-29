package roguelike.effect;

public abstract class AbstractEffect implements Effect {
    protected int amount;
    protected boolean isEmpty;
    protected boolean expired = false;

    public AbstractEffect(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isEmpty() {
        return amount == 0;
    }

    public void expireEffect(){
        expired = true;
    }

    public boolean isExpired(){ return expired; }
}
