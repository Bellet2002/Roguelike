package roguelike.enemy.enemyBehavior;

import java.util.Random;

import roguelike.character.Player;
import roguelike.enemy.Enemy;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class FleeBehavior implements EnemyBehavior {
    private GameMap map;
    private Random rand = new Random();

    public FleeBehavior(GameMap map) {
        this.map = map;
    }

    @Override
    public void behavior(Enemy enemy, Player player) {
        Location current = enemy.getLocation();
        if (enemy.getLocation().getDistance(player.getLocation()) <= 3) {
            int x = rand.nextInt(current.getX() + 1, map.getFrameSize());
            int y = rand.nextInt(current.getY() + 1, map.getFrameSize());

            Location newLocation = new Location(map.getTile(x, y), map);
            enemy.moveTowards(newLocation);
        }
    }
}
