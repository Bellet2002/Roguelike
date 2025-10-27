package roguelike.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

/**
 * Represents a map with or without random elements.
 * 
 */

public class GameMap {
  private static final int FRAME_SIZE = 800;
  private static final int UNIT_SIZE = FRAME_SIZE / 20;
  private int width = UNIT_SIZE;
  private int height = UNIT_SIZE;
  private List<List<Tile>> mapTiles = new ArrayList<>(height);
  private Random rand = new Random();

  private record TileDistance(Tile tile, int distance) {}

  private GameMap() {}

  /**
   * Generates a map with or without random elements.
   * 
   * @param randomized if true then map has random elements, else false
   */
  public static GameMap createGameMap(boolean randomized) {
    GameMap map = new GameMap();
    map.generateMap();
    if (randomized) {
      map.generateRandomTerrain(TileType.MOUNTAIN, 1, 2, 0.8);
      map.generateRandomTerrain(TileType.WATER, 2, 4, 0.5);
      map.generateRandomTerrain(TileType.FOREST, 3, 5, 0.25);
    }
    map.generateRoad();
    return map;
  }

  private void generateMap() {
    for (int i = 0; i < height; i++) {
      mapTiles.add(i, new ArrayList<Tile>());
      for (int j = 0; j < width; j++) {
        mapTiles.get(i).add(j, new Tile(TileType.GRASS, j, i));
      }
    }
  }

  /**
   * Generates random patches of terrain for the map.
   * 
   * @param type  The type of terrain
   * @param minPatches  Minimum allowed amount of patches
   * @param maxPatches  Maximum allowed amount of patches
   * @param patchSize  The approximate size of the patch, expects an double of 0 - 1
   */
  public void generateRandomTerrain(TileType type,
                                    int minPatches, 
                                    int maxPatches, 
                                    double patchSize) {
    if (type == null) {
      throw new IllegalArgumentException();
    }
    if (minPatches < 0 || maxPatches < 0 || minPatches > maxPatches || maxPatches > UNIT_SIZE) {
      throw new IllegalArgumentException();
    }
    if (patchSize < 0 || patchSize > 1) {
      throw new IllegalArgumentException();
    }

    int maxDimension = Math.min(width, height);
    int amount = (minPatches != 0) ? minPatches + rand.nextInt(maxPatches - minPatches + 1) : 1;
    int minSize = 1 + rand.nextInt(Math.max(1, 
                                (int) Math.ceil((maxDimension * patchSize) / 2)));
    int maxSize = minSize + rand.nextInt(Math.max(1, 
                                (int) Math.ceil(maxDimension * patchSize) - minSize));

    int zoneHeight = height / amount;
    int zoneWidth = width / amount;

    for (int i = 0; i < amount; i++) {
      int patchHeight = minSize + rand.nextInt(maxSize - minSize + 1);
      int patchWidth = minSize + rand.nextInt(maxSize - minSize + 1);
      int startY = i * zoneHeight + rand.nextInt(Math.max(1, zoneHeight - patchHeight));
      Set<Tile> currentPatch = new HashSet<>();

      for (int y = startY; y < Math.min(height, startY + patchHeight); y++) {
        int startX = i * zoneWidth;
        for (int x = startX; x < Math.min(width, startX + patchWidth); x++) {
          boolean touchesOtherPatch = false;
          Tile currentTile = mapTiles.get(y).get(x);
          List<Tile> neighbours = findNeighbours(currentTile); 
          for (Tile tile : neighbours) {
            if (tile.getType() == type && !currentPatch.contains(tile)) {
              touchesOtherPatch = true;
              break;
            }
          }
          if (!touchesOtherPatch) {
            mapTiles.get(y).get(x).setType(type);
            currentPatch.add(currentTile);
          }
        }
      }
    }
  }

  /**
   * Generates a road through the map.
   */
  private void generateRoad() {
    List<Tile> points = generateRandomRoadPoints();
    List<Tile> path = generatePath(points.get(0), points.get(1));
    for (Tile tile : path) {
      tile.setType(TileType.ROAD);
    }
    
  }

  /**
   * Uses djikstras algorithm to calculate the fastes path between start and end.
   * 
   * @param start  The starting point
   * @param end  The ending point
   * @return  A list of tiles that represent the path between start and end
   */
  public List<Tile> generatePath(Tile start, Tile end) {
    Map<Tile, Integer> distances = new HashMap<>();
    PriorityQueue<TileDistance> pq = new PriorityQueue<>(
                                        Comparator.comparingInt(TileDistance::distance)
                                        );

    for (List<Tile> row : mapTiles) {
      for (Tile tile : row) {
        distances.put(tile, Integer.MAX_VALUE);
      }
    }

    distances.put(start, 0);
    pq.offer(new TileDistance(start, 0));
    Map<Tile, Tile> previous = new HashMap<>();

    while (!pq.isEmpty()) {
      TileDistance current = pq.poll();
      Tile tile = current.tile();

      for (Tile neighbour : findNeighbours(tile)) {
        int newDistance = distances.get(tile) + neighbour.getWeight();
        if (newDistance < distances.get(neighbour)) {
          distances.put(neighbour, newDistance);
          previous.put(neighbour, tile);
          pq.add(new TileDistance(neighbour, newDistance));
        }
      }
    }
    
    List<Tile> path = new ArrayList<>();
    Tile current = end;
    while (current != null && previous.containsKey(current)) {
      path.add(current);
      current = previous.get(current);
    }
    path.add(start);
    Collections.reverse(path);

    return path;
  }

  /**
   * Finds a tiles neighbouring tiles.
   * 
   * @param tile  The tile whose neighbours should be found
   * @return  A list of the neighbouring tiles
   */
  public List<Tile> findNeighbours(Tile tile) {
    List<Tile> result = new ArrayList<>();
    int x = tile.getPosX();
    int y = tile.getPosY();

    if (y > 0) {
      result.add(mapTiles.get(y - 1).get(x));
    }
    if (y < height - 1) {
      result.add(mapTiles.get(y + 1).get(x));
    }
    if (x > 0) {
      result.add(mapTiles.get(y).get(x - 1));
    }
    if (x < width - 1) {
      result.add(mapTiles.get(y).get(x + 1));
    }
    return result;
  }

  /**
   * Generates two random edges of which to select the starting and end point to the road on.
   * 
   * @return  A list of two tiles on the edges of the map
   */
  public List<Tile> generateRandomRoadPoints() {
    int startEdge = rand.nextInt(4);
    int endEdge;
    do {
      endEdge = rand.nextInt(4);
    } while (startEdge == endEdge);

    List<Tile> result = new ArrayList<>();
    result.add(randomRoadPointsHelper(startEdge));
    result.add(randomRoadPointsHelper(endEdge));

    return result;
  }

  /**
   * Gets a random tile from the edge selected previously.
   * 
   * @see #generateRandomRoadPoints()
   * @param edge  Corresponds to which edge of the map the tile will get picked from
   * @return  A tile to serve as starting or end point
   */
  public Tile randomRoadPointsHelper(int edge) {
    switch (edge) {
      case 0:
        return mapTiles.get(0).get(rand.nextInt(width - 1));
      case 1:
        return mapTiles.get(height - 1).get(rand.nextInt(width - 1));
      case 2:
        return mapTiles.get(rand.nextInt(height - 1)).get(0);
      case 3:
        return mapTiles.get(rand.nextInt(height - 1)).get(width - 1);
      default:
        throw new IllegalArgumentException();
    }
  } 

  /*public void displayMap() {
    String border = "+" + " -".repeat(width) + " +";
    System.out.println(border);
    for (List<Tile> list : mapTiles) {
        System.out.print(" ");
        for (Tile tile : list) {
            System.out.print( " " + tile.getSymbol());
        }
        System.out.println();
    }
    System.out.println(border);
  }*/

  public List<List<Tile>> getMap() {
    return Collections.unmodifiableList(mapTiles);
  }

  /**
   * Finds the tile at the given position, wraps around if position is out of bounds.
   * 
   * @param x  The x position in the matrix
   * @param y  The y position in the matrix
   * @return  The corresponding tile
   */
  public Tile getTile(int x, int y) {
    int newX = ((x % width) + width) % width;
    int newY = ((y % height) + height) % height;

    return mapTiles.get(newY).get(newX);
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height; 
  }
  
  public int getFrameSize() {
    return FRAME_SIZE;
  }
}