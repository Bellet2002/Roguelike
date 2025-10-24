package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public interface EnemyBehavior {
    void behavior(Enemy enemy, Player player);
}
