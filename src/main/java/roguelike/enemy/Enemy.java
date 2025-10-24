package roguelike.enemy;

import roguelike.enemy.enemyBehavior.EnemyPersonality;
import roguelike.map.Location;
import roguelike.character.Character;

public class Enemy extends Character {
    private EnemyPersonality personality;

    public Enemy(String name, int health, int level, Location location, EnemyPersonality personality) {
        super(name, health, level, location);
        this.personality = personality;
    }

    public void moveTowards(Location target) {
        int dx = Integer.compare(target.getX(), this.getLocation().getX());
        int dy = Integer.compare(target.getY(), this.getLocation().getY());

        if (dx < 0) {
            super.move(Direction.LEFT);
        } else if (dx > 0) {
            super.move(Direction.RIGHT);
        } else if (dy < 0) {
            super.move(Direction.UP);
        } else if (dy > 0) {
            super.move(Direction.DOWN);
        }
    }

    public void speak(String line) {
        System.out.println(line);
    }

    private EnemyPersonality getPersonality() { return personality; }
}
