package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

/**
 * Represent an enemies behavior while either idling or active.
 */
public interface EnemyBehavior {
  /**
   * The logic for the different behaviors.
   */
  void behavior(Enemy enemy, Player player);
}
