package roguelike.item;

import roguelike.character.Character;
import roguelike.character.Player;

public interface Weapon {
    void attack(Character character, Player player);
}
