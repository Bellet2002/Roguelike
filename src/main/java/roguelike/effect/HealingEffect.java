package roguelike.effect;

import roguelike.Character;
import roguelike.item.Item;

public class HealingEffect implements Effect {
    @Override
    public void apply(Character character, Item item) {
        //int healingAmount = item.getPower();
        //character.heal(healingAmount); (requires heal() method in Character?)
    }
}
