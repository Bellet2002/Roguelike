package roguelike.effect;

import roguelike.character.Character;
import roguelike.item.Item;

//Strategy pattern to define different item behaviors that may be applied to a character
public interface Effect {
    void apply(Character character, Item item);
}



