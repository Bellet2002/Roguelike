package roguelike.effect;

import roguelike.character.Character;

public class HealingEffect extends AbstractEffect {

    public HealingEffect(int amount) {
        super(amount);
    }

    @Override
    public void apply(Character character) {
        int oldHp = character.getHp();
        character.heal(amount);

        int healed = character.getHp() - oldHp;
        if (character.getHp() > oldHp) {
            amount -= healed;
        }
    }
}
