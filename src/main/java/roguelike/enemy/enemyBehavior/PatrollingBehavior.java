package roguelike.enemy.enemyBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import roguelike.character.Player;
import roguelike.enemy.Enemy;
import roguelike.map.GameMap;
import roguelike.map.Location;
import roguelike.map.Tile;

public class PatrollingBehavior implements EnemyBehavior{
    private List<Location> patrolPoints = new ArrayList<>();
    private int currentLocation = 0;

    private Random rand = new Random();

    public PatrollingBehavior(GameMap map) {
        for (int i = 0; i < 3; i++) {
            int x = rand.nextInt(map.getFrameSize());
            int y = rand.nextInt(map.getFrameSize());
            Tile tile = map.getTile(x, y);
            patrolPoints.add(new Location(tile, map));
        }
    }

    public PatrollingBehavior(List<Location> patrolPoints) {
        this.patrolPoints = patrolPoints;
    }

    @Override
    public void behavior(Enemy enemy, Player player) {
        Location next = patrolPoints.get(currentLocation);
        enemy.moveTowards(next);
        currentLocation = (currentLocation + 1) % patrolPoints.size();
    }
}
