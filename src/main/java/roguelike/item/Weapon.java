package roguelike.item;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public interface Weapon {
    void attack(Enemy enemy, Player player);
}
