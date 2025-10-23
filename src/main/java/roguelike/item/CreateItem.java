package roguelike.item;

import java.util.Objects;

import roguelike.character.Character;
import roguelike.effect.Effect;

//Concrete item class that creates different items based on enum ItemType and
//uses strategy pattern via Effect to define individual behavior/effects
public class CreateItem implements Item {
    private final String name;
    private final int power;
    private final ItemType type;
    private final int levelRequirement;
    private final Effect effect;

    public CreateItem(String name, int power, ItemType type, int levelRequirement, Effect effect) {
        this.name = name;
        this.power = power;
        this.type = type;
        this.levelRequirement = levelRequirement;
        this.effect = effect;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPower() {
        return power;
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
    public boolean canUse(Character character) {
        return character.getLevel() >= levelRequirement;
    }

    @Override
    public void use(Character character) {
        effect.apply(character, this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateItem that = (CreateItem) o;
        return power == that.power &&
            levelRequirement == that.levelRequirement &&
            name.equals(that.name) &&
            type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power, type, levelRequirement);
    }
}
