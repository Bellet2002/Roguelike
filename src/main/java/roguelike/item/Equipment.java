package roguelike.item;

public class Equipment extends AbstractItem {
    private final int stat;

    public Equipment(String name, ItemType type, int levelRequirement, int stat) {
        super(name, type, levelRequirement);
        this.stat = stat;
    }

    public int getStat() {
        return stat;
    }
}
