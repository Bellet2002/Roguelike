package roguelike.enemy.enemybehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

/**
 * Represents the behavior of an enemy chasing and attacking the player.
 */
public class ChaseBehavior implements EnemyBehavior {
  @Override
  public void behavior(Enemy enemy, Player player) {
    if (enemy.getLocation().getDistance(player.getLocation()) <= 1) {
      enemy.attack(enemy, player);
    } else {
      enemy.moveTowards(player.getLocation());
      enemy.attack(enemy, player);
    }
  }
}
