package roguelike.enemy.enemyBehavior;

import java.util.Random;

import roguelike.character.Player;
import roguelike.enemy.Enemy;
import roguelike.map.GameMap;
import roguelike.map.Location;

/**
 * Represent the behavior of an enemy fleeing.
 */
public class FleeBehavior implements EnemyBehavior {
  private GameMap map;
  private Random rand = new Random();

  /**
   * Creates the behavior and assigns the relevant map.
   * 
   * @param map  The map the enemy is in, is used to get random locations to run towards
   * @see #behavior(Enemy, Player)
   */
  public FleeBehavior(GameMap map) {
    this.map = map;
  }

  @Override
  public void behavior(Enemy enemy, Player player) {
    int curX = enemy.getLocation().getX();
    int curY = enemy.getLocation().getY();
    int playerX = player.getLocation().getX();
    int playerY = player.getLocation().getY();

    int minX;
    int maxX;
    if (curX < playerX) {
      minX = 0;
      maxX = curX - 2;
    } else {
      minX = curX + 2;
      maxX = map.getWidth() - 1;
    }
    int minY;
    int maxY;
    if (curY < playerY) {
      minY = 0;
      maxY = curY - 2;
    } else {
      minY = curY + 2;
      maxY = map.getHeight() - 1;
    }

    int targetX = rand.nextInt(minX, maxX + 1);
    int targetY = rand.nextInt(minY, maxY + 1);
    enemy.moveTowards(new Location(map.getTile(targetX, targetY), map));
  }
}
