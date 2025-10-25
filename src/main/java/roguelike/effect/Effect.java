package roguelike.effect;

import roguelike.character.Character;

//Strategy pattern to define different effects (buff/debuff) that may be applied to a character
public interface Effect {
    void apply(Character character);
}



