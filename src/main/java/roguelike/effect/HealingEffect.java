package roguelike.effect;

import roguelike.character.Character;

public class HealingEffect extends AbstractEffect {

    public HealingEffect(int amount) {
        super(amount);
    }

    @Override
    public void apply(Character character) {
        //character.heal(amount);
    }
}
