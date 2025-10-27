package roguelike.enemy;

import roguelike.character.Character;
import roguelike.enemy.enemybehavior.EnemyPersonality;
import roguelike.map.Location;

/**
 * Represents an enemy, extends Character.
 */
public class Enemy extends Character {
  private EnemyPersonality personality;
  private boolean isAttacking = false;
  private boolean hasSpoken = false;

  /**
   * Creates an enemy.
   * 
   * @param name  Name of the enemy
   * @param health  The enemies health
   * @param level  The enemies level
   * @param location  The enemies Location
   * @param personality  The enemies behavior while active or idle
   */
  public Enemy(String name,
               int health,
               int level,
               Location location,
               EnemyPersonality personality) {
    super(name, health, level, location);
    this.personality = personality;
  }

  /**
   * Makes the enemy move towards a location if it is alive and more than one step away.
   * 
   * @param target  The targetted location
   */
  public void moveTowards(Location target) {
    while (getLocation().getDistance(target) > 1 && this.isAlive()) {
      int x = Integer.compare(target.getX(), this.getLocation().getX());
      int y = Integer.compare(target.getY(), this.getLocation().getY());

      if (x < 0) {
        super.move(Direction.LEFT);
      } else if (x > 0) {
        super.move(Direction.RIGHT);
      } else if (y < 0) {
        super.move(Direction.UP);
      } else if (y > 0) {
        super.move(Direction.DOWN);
      }
    }
  }

  /**
   * Makes the enemy attack the target.
   * 
   * @param target  The targeted character
   */
  public void attack(Character target) {
    isAttacking = (this.isAlive()) ? true : false;
    if (isAttacking) {
      super.attack(target);
    }
  }

  /**
   * Sets hasSpoken true if the enemy speaks.
   * 
   * @param line  The enemies speech/line
   */
  public void speak(String line) {
    hasSpoken = true;
  }

  public EnemyPersonality getPersonality() {
    return personality;
  }

  /**
   * Returns if the enemy is attacking.
   * 
   * @return A boolean that is true if the enemy is attacking and false otherwise
   */
  public boolean isAttacking() {
    return isAttacking;
  }

  /**
   * Returns if the enemy has spoken for testing purposes.
   * 
   * @return A boolean that is true if the enemy has spoken and false otherwise.
   */
  public boolean hasSpoken() {
    return hasSpoken;
  }
}
