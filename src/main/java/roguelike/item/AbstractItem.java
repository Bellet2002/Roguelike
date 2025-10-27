package roguelike.item;

import java.util.Objects;

//Abstract item class implementing interface Item that creates base for other subclasses
public abstract class AbstractItem implements Item {
    private final String name;
    private final ItemType type;
    protected final int levelRequirement;

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
    public int hashCode() {
        return Objects.hash(getName(), getType());
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof AbstractItem item){
            return getName().equals(item.getName());
        }
        return false;
    }
}
