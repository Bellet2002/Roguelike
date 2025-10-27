package roguelike.enemy.enemybehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

/**
 * Represents the behavior of an enemy just standing around.
 */
public class StandingBehavior implements EnemyBehavior {
  @Override
  public void behavior(Enemy enemy, Player player) {
    if (enemy.getLocation().getDistance(player.getLocation()) <= 20) {
      enemy.speak("Alert!");
    }
  }
}
