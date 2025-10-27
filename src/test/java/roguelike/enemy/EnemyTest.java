package roguelike.enemy;

import roguelike.character.Player;
import roguelike.character.Character.Direction;
import roguelike.enemy.enemyBehavior.*;
import roguelike.map.GameMap;
import roguelike.map.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class EnemyTest {
    @Test
    void testMoveTowardsLocation() {
        GameMap map = new GameMap(false);
        Enemy goblin = new Enemy(
                            "Goblin",
                            30,
                            2,
                            new Location(map.getTile(0, 0), map),
                            new EnemyPersonality(
                                new PatrollingBehavior(map),
                                new ChaseBehavior()
                            )
        );

        Location location1 = new Location(map.getTile(0, 2), map);
        Location location2 = new Location(map.getTile(5, 2), map);
        Location location3 = new Location(map.getTile(7, 8), map);
        goblin.moveTowards(location1);
        assertTrue(location1.isNeighbour(goblin.getLocation()));
        goblin.moveTowards(location2);
        assertTrue(location2.isNeighbour(goblin.getLocation()));
        goblin.moveTowards(location3);
        assertTrue(location3.isNeighbour(goblin.getLocation()));
    }

    @Test
    void testMoveTowardsPlayerAndChaseBehavior() {
        GameMap map = new GameMap(false);
        Enemy goblin = new Enemy(
                            "Goblin",
                            30,
                            2,
                            new Location(map.getTile(0, 0), map),
                            new EnemyPersonality(
                                new ChaseBehavior(),
                                new PatrollingBehavior(map)
                            )
        );
        Player character = new Player("Sacrifice", 50, new Location(map.getTile(0, 10), map));
        assertFalse(goblin.IsAttacking());
        goblin.moveTowards(character.getLocation());
        assertTrue(character.getLocation().isNeighbour(goblin.getLocation()));
        goblin.getPersonality().behavior(goblin, character);
        assertTrue(goblin.IsAttacking());
        goblin.takeDamage(30);
        assertFalse(goblin.isAlive());
        goblin.getPersonality().behavior(goblin, character);
        assertFalse(goblin.IsAttacking());
    }

    @Test
    void testChaseBehavior() {
        GameMap map = new GameMap(false);
        Enemy goblin = new Enemy(
                            "Goblin",
                            30,
                            2,
                            new Location(map.getTile(0, 0), map),
                            new EnemyPersonality(
                                new ChaseBehavior(),
                                new PatrollingBehavior(map)
                            )
        );
        Player character = new Player("Sacrifice", 50, new Location(map.getTile(5, 5), map));
        goblin.getPersonality().behavior(goblin, character);
        assertTrue(character.getLocation().isNeighbour(goblin.getLocation()));
        assertTrue(goblin.IsAttacking());
    }

    @Test
    void testFleeBehaviorUp() {
        GameMap map = new GameMap(false);
        Enemy squirrel = new Enemy(
                            "Squirrel",
                            5,
                            1,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new FleeBehavior(map),
                                new StandingBehavior()
                            )
        );
        Player character = new Player("Villain", 50, new Location(map.getTile(10, 5), map));
        squirrel.getPersonality().behavior(squirrel, character);
        assertTrue(squirrel.getLocation().getY() > 10);
    }

    @Test
    void testFleeBehaviorDown() {
        GameMap map = new GameMap(false);
        Enemy squirrel = new Enemy(
                            "Squirrel",
                            5,
                            1,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new FleeBehavior(map),
                                new StandingBehavior()
                            )
        );
        Player character = new Player("Villain", 50, new Location(map.getTile(10, 15), map));
        squirrel.getPersonality().behavior(squirrel, character);
        assertTrue(squirrel.getLocation().getY() < 10);
    }

    @Test
    void testFleeBehaviorRight() {
        GameMap map = new GameMap(false);
        Enemy squirrel = new Enemy(
                            "Squirrel",
                            5,
                            1,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new FleeBehavior(map),
                                new StandingBehavior()
                            )
        );
        Player character = new Player("Villain", 50, new Location(map.getTile(5, 10), map));
        squirrel.getPersonality().behavior(squirrel, character);
        assertTrue(squirrel.getLocation().getX() > 10);
    }

    @Test
    void testFleeBehaviorLeft() {
        GameMap map = new GameMap(false);
        Enemy squirrel = new Enemy(
                            "Squirrel",
                            5,
                            1,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new FleeBehavior(map),
                                new StandingBehavior()
                            )
        );
        Player character = new Player("Villain", 50, new Location(map.getTile(15, 10), map));
        squirrel.getPersonality().behavior(squirrel, character);
        assertTrue(squirrel.getLocation().getX() < 10);
    }

    @Test
    void testPatrollingBehavior() {
        GameMap map = new GameMap(false);
        List<Location> patrolPoints = List.of(
            new Location(map.getTile(5, 5), map),
            new Location(map.getTile(10, 10), map),
            new Location(map.getTile(20, 20), map)
        );
        Enemy goblin = new Enemy(
                            "Goblin",
                            30,
                            2,
                            new Location(map.getTile(0, 0), map),
                            new EnemyPersonality(
                                new ChaseBehavior(),
                                new PatrollingBehavior(patrolPoints)
                            )
        );

        for (int i = 0; i < 6; i++) {
            goblin.getPersonality().getIdle().behavior(goblin, null);
            assertTrue(patrolPoints.get(i % patrolPoints.size()).isNeighbour(goblin.getLocation()));
        }
        Location location = goblin.getLocation();
        goblin.takeDamage(30);
        assertFalse(goblin.isAlive());
        goblin.moveTowards(new Location(map.getTile(0, 0), map));
        assertEquals(location, goblin.getLocation());
    }

    @Test
    void testSpeakAndStandingBehavior() {
        GameMap map = new GameMap(false);
        Enemy squirrel = new Enemy(
                            "Squirrel",
                            5,
                            1,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new FleeBehavior(map),
                                new StandingBehavior()
                            )
        );
        Player character = new Player("Villain", 50, new Location(map.getTile(map.getHeight()-1, map.getWidth()-1), map));
        squirrel.getPersonality().behavior(squirrel, character);
        assertFalse(squirrel.hasSpoken());
        Player otherCharacter = new Player("Villain", 50, new Location(map.getTile(5, 17), map));
        squirrel.getPersonality().behavior(squirrel, otherCharacter);
        assertTrue(squirrel.hasSpoken());
    }
    
    @Test
    void testActiveToIdle() {
        GameMap map = new GameMap(false);
        Enemy goblin = new Enemy(
                            "Goblin",
                            30,
                            2,
                            new Location(map.getTile(10, 10), map),
                            new EnemyPersonality(
                                new ChaseBehavior(),
                                new PatrollingBehavior(map)
                            )
        );
        Player character = new Player("Sacrifice", 50, new Location(map.getTile(7, 11), map));
        goblin.getPersonality().behavior(goblin, character);

        assertTrue(goblin.IsAttacking());
        assertTrue(character.getLocation().isNeighbour(goblin.getLocation()));
        for (int i = 0; i < 20; i++) {
            character.move(Direction.RIGHT);
        }
        goblin.getPersonality().behavior(goblin, character);
        assertFalse(character.getLocation().isNeighbour(goblin.getLocation()));
    }
}
