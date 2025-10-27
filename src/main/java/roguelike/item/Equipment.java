package roguelike.item;

import roguelike.character.Character;

public class Equipment extends AbstractItem {
    private final int stat;
    protected int durability;

    public Equipment(String name, ItemType type, int levelRequirement, int stat, int durability) {
        super(name, type, levelRequirement);
        this.stat = stat;
        this.durability = durability;
    }

    public int getStat() {
        return stat;
    }

    public int getDurability() {
        return durability;
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public boolean canUse(Character player) {
        return player.getLevel() >= levelRequirement && !isBroken();
    }
}
