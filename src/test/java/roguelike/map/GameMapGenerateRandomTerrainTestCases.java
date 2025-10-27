package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// ChatGPT-skrivna test baserade på testfallen från ekvivalensklasserna av generateRandomTerrain()
// Den första rundan kod var dock inte tillräckligt specifik
// utan kollade bara att minst en instans existerade.
// Så jag påpekade detta och den gav mig då följande kod.
// Denna fungerar dock inte heller, då den räknade clusters fel.
// Så vid det här laget så ger jag upp på att prompta och fixar den existerande koden själv.
// Jag behövde tona ner metodens randomness rätt mycket för att få stabila resultat.
// Man kan dock inte lita på att bara för att ChatGPT säger att testerna är rätt och dem går igenom
// Att testerna faktiskt testar rätt saker.

/**
 * AI generated tests for the method generateRandomTerrain() in GameMap.
 */
public class GameMapGenerateRandomTerrainTestCases {

  private GameMap map;

  // Detta visste jag inte att man kunde göra
  // Så en tydlig positiv med AI-användning i detta stadiet är att man lär sig mycket av syntaxen
  @BeforeEach
  void setUp() {
    map = new GameMap(false);
  }

  private int countClusters(TileType type) {
    boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
    int clusters = 0;

    for (int y = 0; y < map.getHeight(); y++) {
      for (int x = 0; x < map.getWidth(); x++) {
        Tile t = map.getTile(x, y);
        if (t.getType() == type && !visited[y][x]) {
          clusters++;
          markCluster(x, y, type, visited);
        }
      }
    }
    return clusters;
  }

  private void markCluster(int x, int y, TileType type, boolean[][] visited) {
    if (x < 0 || y < 0 || x >= map.getWidth() || y >= map.getHeight()) {
      return;
    }
    if (visited[y][x]) {
      return;
    }
    if (map.getTile(x, y).getType() != type) {
      return;
    }

    visited[y][x] = true;
    markCluster(x + 1, y, type, visited);
    markCluster(x - 1, y, type, visited);
    markCluster(x, y + 1, type, visited);
    markCluster(x, y - 1, type, visited);
  }

  // ----------- Test cases -----------

  // T1: 101, 301, 201
  @Test
  void testT1ForestNormalRange() {
    map.generateRandomTerrain(TileType.FOREST, 2, 5, 0.5);
    int clusters = countClusters(TileType.FOREST);
    assertTrue(clusters >= 2 && clusters <= 5, "Should generate 2-5 forest patches");
  }

  // T2: 102, 301, 201
  @Test
  void testT2MountainFixedCount() {
    map.generateRandomTerrain(TileType.MOUNTAIN, 3, 3, 0.4);
    int clusters = countClusters(TileType.MOUNTAIN);
    assertEquals(3, clusters, "Should generate exactly 3 mountain patches");
  }

  // T3: 103, 301, 202
  @Test
  void testT3WaterZeroMinDoesNotCrash() {
    assertDoesNotThrow(() -> map.generateRandomTerrain(TileType.WATER, 0, 4, 0.6));
  }

  // T4: 104, 301, 201
  @Test
  void testT4NegativeMinThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.FOREST, -1, 4, 0.5));
  }

  // T5: 105, 301, 201
  @Test
  void testT5NegativeMaxThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.WATER, 2, -3, 0.5));
  }

  // T6: 106, 301, 201
  @Test
  void testT6MaxLessThanMinThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.MOUNTAIN, 5, 3, 0.5));
  }

  // T7: 107, 301, 201
  @Test
  void testT7MaxTooLargeThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.FOREST, 2, 1000, 0.5));
  }

  // T8: 101, 301, 203
  @Test
  void testT8WaterSmallPatchSize() {
    map.generateRandomTerrain(TileType.WATER, 2, 3, 0.1);
    int clusters = countClusters(TileType.WATER);
    assertTrue(clusters >= 2 && clusters <= 3, "Should generate 2-3 small water patches");
  }

  // T9: 102, 301, 204
  @Test
  void testT9MountainLargePatch() {
    map.generateRandomTerrain(TileType.MOUNTAIN, 1, 1, 0.99);
    int clusters = countClusters(TileType.MOUNTAIN);
    assertEquals(1, clusters, "Should generate one large mountain patch");
  }

  // T10: 101, 301, 205
  @Test
  void testT10NegativePatchSizeThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.FOREST, 3, 5, -0.5));
  }

  // T11: 101, 301, 206
  @Test
  void testT11TooLargePatchSizeThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(TileType.FOREST, 3, 5, 1.5));
  }

  // T12: 101, 302, 201
  @Test
  void testT12NullTypeThrows() {
    assertThrows(IllegalArgumentException.class, () ->
            map.generateRandomTerrain(null, 2, 4, 0.5));
  }
}

