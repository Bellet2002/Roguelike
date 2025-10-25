package roguelike.item;

import roguelike.character.Character;

//Abstract item class implementing interface Item that creates base for other subclasses
public abstract class AbstractItem implements Item {
    private final String name;
    private final ItemType type;
    private final int levelRequirement;

    public AbstractItem(String name, ItemType type, int levelRequirement) {
        this.name = name;
        this.type = type;
        this.levelRequirement = levelRequirement;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public int getLevelRequirement() {
        return levelRequirement;
    }

    @Override
    public boolean canUse(Character player) {
        return player.getLevel() >= levelRequirement;
    }
}
