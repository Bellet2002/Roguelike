package roguelike.item;

import roguelike.character.Player;

public class Equipment extends AbstractItem {
    private final int stat;

    public Equipment(String name, ItemType type, int levelRequirement, int stat) {
        super(name, type, levelRequirement);
        this.stat = stat;
    }

    public int getStat() {
        return stat;
    }

    @Override
    public void use(Player player) {
        if (canUse(player)) {
            
        }
    }
}
