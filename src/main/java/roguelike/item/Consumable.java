package roguelike.item;

import java.util.Objects;

import roguelike.character.Character;
import roguelike.effect.AbstractEffect;

public class Consumable extends AbstractItem {
    private final AbstractEffect effect;

    public Consumable(String name, ItemType type, int levelRequirement, AbstractEffect effect) {
        super(name, type, levelRequirement);
        this.effect = effect;
    }

    public AbstractEffect getEffect() {
        return effect;
    }

    @Override
    public void use(Character player) {
        if (canUse(player) || effect.getAmount() != 0) {
            effect.apply(player);
        }
    }
}
