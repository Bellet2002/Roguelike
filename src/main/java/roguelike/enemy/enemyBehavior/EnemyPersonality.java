package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

/**
 * Represents a combination of idling and active behavior.
 */
public class EnemyPersonality implements EnemyBehavior {
  private final EnemyBehavior active;
  private final EnemyBehavior idle;
  private boolean visiblePlayer = false;

  /**
   * Sets the different behaviors.
   * 
   * @param active  The behavior when the enemy is close to the player
   * @param idle  The behavior when the enemy is not close to the player
   */
  public EnemyPersonality(EnemyBehavior active, EnemyBehavior idle) {
    this.active = active;
    this.idle = idle;
  }

  @Override
  public void behavior(Enemy enemy, Player player) {
    visiblePlayer = (enemy.getLocation().getDistance(player.getLocation()) <= 10) ? true : false;
    if (visiblePlayer) {
      active.behavior(enemy, player);
    } else {
      idle.behavior(enemy, player);
    }
  }

  public EnemyBehavior getIdle() {
    return idle;
  }
}
