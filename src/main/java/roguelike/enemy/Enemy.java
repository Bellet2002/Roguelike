package roguelike.enemy;

import roguelike.enemy.enemyBehavior.EnemyPersonality;
import roguelike.map.Location;
import roguelike.character.Character;

public class Enemy extends Character {
    private EnemyPersonality personality;
    private boolean isAttacking = false;
    private boolean hasSpoken = false;

    public Enemy(String name, int health, int level, Location location, EnemyPersonality personality) {
        super(name, health, level, location);
        this.personality = personality;
    }

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

    public void attack(Character target) {
        isAttacking = (this.isAlive()) ? true : false;
    }

    public void speak(String line) {
        hasSpoken = true;
    }

    public EnemyPersonality getPersonality() { return personality; }
    public boolean IsAttacking() { return isAttacking; }
    public boolean hasSpoken() { return hasSpoken; }
}
