package roguelike.map;

import roguelike.character.Character.Direction;

/**
 * Represents a characters location on the map.
 */

public class Location {
  Tile tile;
  GameMap map;

  /**
   * Creates a location with a corresponding tile and map.
   * 
   * @param tile  The starting tile
   * @param map  The map the location exists within
   */
  public Location(Tile tile, GameMap map) {
    this.tile = tile;
    this.map = map;
  }

  /**
   * The movement logic for going from tile to tile.
   * 
   * @param direct  The direction for the movement
   * @see rougelike.character.Character
   */
  public void move(Direction direct) {
    int x = this.getX() + direct.x;
    int y = this.getY() + direct.y;
    Tile newTile = map.getTile(x, y);
    tile = newTile;
  }

  /**
   * Gets the distance in vertical or horizontal steps from one location to the other.
   * 
   * @param other  The goal destination
   * @return  An int representing the distance in amount of steps
   */
  public int getDistance(Location other) {
    return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() - other.getY());
  }

  /**
   * Checks if the other location is right next to this.
   * 
   * @param location  The location being compared
   * @return  A boolean which is true if they are neighbours and false otherwise
   */
  public boolean isNeighbour(Location location) {
    return map.findNeighbours(tile).contains(location.getTile());
  }

  public int getX() {
    return tile.getPosX();
  }

  public int getY() {
    return tile.getPosY();
  }

  public Tile getTile() {
    return tile;
  }

  public GameMap getMap() {
    return map;
  }
    
}
