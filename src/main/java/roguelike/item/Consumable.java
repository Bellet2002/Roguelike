package roguelike.item;

import roguelike.character.Character;
import roguelike.effect.Effect;

public class Consumable extends AbstractItem {
    private final Effect effect;

    public Consumable(String name, ItemType type, int levelRequirement, Effect effect) {
        super(name, type, levelRequirement);
        this.effect = effect;
    }

    public Effect getEffect() {
        return effect;
    }

    @Override
    public void use(Character character) {
        if (canUse(character)) {
            effect.apply(character);
            
        }
    }
}
