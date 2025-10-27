package roguelike.enemy.enemybehavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import roguelike.character.Player;
import roguelike.enemy.Enemy;
import roguelike.map.GameMap;
import roguelike.map.Location;
import roguelike.map.Tile;

/**
 * Represents the behavior of an enemy patrolling.
 */
public class PatrollingBehavior implements EnemyBehavior {
  private List<Location> patrolPoints = new ArrayList<>();
  private int currentLocation = 0;
  private static Random rand = new Random();

  /**
   * Creates a list of random locations for the enemy to patrol between.
   * 
   * @param map  Uses the map to selext the points
   */
  public PatrollingBehavior(GameMap map) {
    for (int i = 0; i < 3; i++) {
      int x = rand.nextInt(map.getFrameSize());
      int y = rand.nextInt(map.getFrameSize());
      Tile tile = map.getTile(x, y);
      patrolPoints.add(new Location(tile, map));
    }
  }

  /**
   * A constructor for when the patrolpoints are given directly.
   * 
   * @param patrolPoints  A list of locations to travel between
   */
  public PatrollingBehavior(List<Location> patrolPoints) {
    this.patrolPoints = Collections.unmodifiableList(patrolPoints);
  }

  @Override
  public void behavior(Enemy enemy, Player player) {
    Location next = patrolPoints.get(currentLocation);
    enemy.moveTowards(next);
    currentLocation = (currentLocation + 1) % patrolPoints.size();
  }
}
