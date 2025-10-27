package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import roguelike.character.Character.Direction;

/**
 * Test for the location class.
 */
public class LocationTest {
  @Test
  void testGetDistance() {
    GameMap map = GameMap.createGameMap(false);
    Location from = new Location(map.getTile(0, 0), map);
    Location to = new Location(map.getTile(0, 6), map);
    assertEquals(6, from.getDistance(to));
    to = new Location(map.getTile(4, 4), map);
    assertEquals(8, from.getDistance(to));
    to = new Location(map.getTile(map.getWidth() - 1, map.getHeight() - 1), map);
    assertEquals(78, from.getDistance(to));
  }

  // Getters-testerna är skrivna av ChatGpt. Bra med kommentarer på testerna.
  @Test
  void testGetMap() {
    GameMap map = GameMap.createGameMap(false);
    Location from = new Location(map.getTile(0, 0), map);
    assertEquals(map, from.getMap(), "Location should return the correct map");
  }

  @Test
  void testGetTile() {
    GameMap map = GameMap.createGameMap(false);
    Tile tile = map.getTile(0, 0);
    Location from = new Location(tile, map);
    assertEquals(tile, from.getTile(), "Location should return the correct tile");
  }

  @Test
  void testGetX() {
    GameMap map = GameMap.createGameMap(false);
    Location from = new Location(map.getTile(3, 7), map);
    assertEquals(3, from.getX(), "Location.getX() should return the tile's X coordinate");
  }

  @Test
  void testGetY() {
    GameMap map = GameMap.createGameMap(false);
    Location from = new Location(map.getTile(3, 7), map);
    assertEquals(7, from.getY(), "Location.getY() should return the tile's Y coordinate");
  }

  // Även dessa är skrivna av AI då idéen är enkel men lite långdragen att skriva:
  // Prompten: Visade Location-konstruktorn och förklarade isNeighbour.
  // Frågade ChatGPT att skapa en tile för varje riktning och testa dem.
  // Den la till far själv.
  @Test
  void testIsNeighbour() {
    GameMap map = GameMap.createGameMap(false);
    Location center = new Location(map.getTile(5, 5), map);
    Location up = new Location(map.getTile(5, 4), map);
    assertTrue(center.isNeighbour(up), "Up tile should be neighbour");
    Location down = new Location(map.getTile(5, 6), map);
    assertTrue(center.isNeighbour(down), "Down tile should be neighbour");
    Location left = new Location(map.getTile(4, 5), map);
    assertTrue(center.isNeighbour(left), "Left tile should be neighbour");
    Location right = new Location(map.getTile(6, 5), map);
    assertTrue(center.isNeighbour(right), "Right tile should be neighbour");
    Location far = new Location(map.getTile(7, 7), map);
    assertFalse(center.isNeighbour(far), "Far tile should not be neighbour");
  }

  // Liknande ovan, mycket kod inte särskilt komplicerat. Den löste båda bra.
  @Test
  void testMove() {
    GameMap map = GameMap.createGameMap(false);
    Location start = new Location(map.getTile(5, 5), map);

    start.move(Direction.UP);
    assertEquals(5, start.getX(), "X coordinate should not change when moving UP");
    assertEquals(4, start.getY(), "Y coordinate should decrease by 1 when moving UP");

    start.move(Direction.DOWN);
    assertEquals(5, start.getX(), "X coordinate should not change when moving DOWN");
    assertEquals(5, start.getY(), "Y coordinate should return to original Y after moving DOWN");

    start.move(Direction.LEFT);
    assertEquals(4, start.getX(), "X coordinate should decrease by 1 when moving LEFT");
    assertEquals(5, start.getY(), "Y coordinate should not change when moving LEFT");

    start.move(Direction.RIGHT);
    assertEquals(5, start.getX(), "X coordinate should return to original X after moving RIGHT");
    assertEquals(5, start.getY(), "Y coordinate should not change when moving RIGHT");
  }
}
