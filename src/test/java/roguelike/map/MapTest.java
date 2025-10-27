package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * Test suite for the class GameMap.
 */
public class MapTest {
  @Test
  void mapIsCorrectSize() {
    GameMap map = GameMap.createGameMap(false);
    List<List<Tile>> list = map.getMap();
    assertEquals(list.size(), map.getHeight());
    assertEquals(list.get(0).size(), map.getWidth());
  }

  @Test
  void terrainIsGenerated() {
    GameMap map = GameMap.createGameMap(true);

    Set<TileType> foundTerrain = new HashSet<>();
    for (List<Tile> row : map.getMap()) {
      for (Tile tile : row) {
        foundTerrain.add(tile.getType());
      }
    }

    for (TileType terrain : TileType.values()) {
      assertTrue(foundTerrain.contains(terrain));
    }
  }

  @Test
  void findsCorrectNeighbours() {
    int y = 4;
    int x = 4;
    GameMap map = GameMap.createGameMap(false);
    List<List<Tile>> mapTiles = map.getMap();
    Tile tile = mapTiles.get(y).get(x);
    List<Tile> neighbours = map.findNeighbours(tile);

    assertEquals(mapTiles.get(y - 1).get(x), neighbours.get(0));
    assertEquals(mapTiles.get(y + 1).get(x), neighbours.get(1));
    assertEquals(mapTiles.get(y).get(x - 1), neighbours.get(2));
    assertEquals(mapTiles.get(y).get(x + 1), neighbours.get(3));
  }

  @Test
  void generatesRoadpointsOnBorder() {
    GameMap map = GameMap.createGameMap(false);
    List<List<Tile>> mapTiles = map.getMap();
    List<Tile> points = map.generateRandomRoadPoints();
    Tile start = points.get(0);
    Tile end = points.get(1);

    List<Tile> topRow = mapTiles.get(0);
    List<Tile> botRow = mapTiles.get(map.getHeight() - 1);
    List<Tile> leftRow = new ArrayList<>();
    List<Tile> rightRow = new ArrayList<>();

    for (List<Tile> list : mapTiles) {
      leftRow.add(list.get(0));
      rightRow.add(list.get(map.getWidth() - 1));
    }
    
    assertTrue(topRow.contains(start) 
                || botRow.contains(start)
                || leftRow.contains(start)
                || rightRow.contains(start)
    );
    assertTrue(topRow.contains(end) 
                || botRow.contains(end)
                || leftRow.contains(end)
                || rightRow.contains(end)
    );
  }

  @Test
  void testInvalidEdgeInRoadPointHelper() {
    GameMap map = GameMap.createGameMap(false);
    assertThrows(IllegalArgumentException.class, () -> 
                    map.randomRoadPointsHelper(6)
    );
  }

  @Test
  void findsPath() {
    GameMap map = GameMap.createGameMap(false);
    List<List<Tile>> mapTiles = map.getMap();

    Tile start = mapTiles.get(0).get(0);
    Tile end = mapTiles.get(map.getHeight() - 1).get(map.getWidth() - 1);
    List<Tile> path = map.generatePath(start, end);
    
    assertNotNull(path);
    assertFalse(path.isEmpty());
    assertEquals(start, path.get(0));
    assertEquals(end, path.get(path.size() - 1));

    for (int i = 0; i < path.size() - 1; i++) {
      Tile current = path.get(i);
      Tile next = path.get(i + 1);
      assertTrue(map.findNeighbours(current).contains(next));
    }
  }
}
