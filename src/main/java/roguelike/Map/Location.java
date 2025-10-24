package roguelike.map;

import roguelike.character.Character.Direction;

public class Location {
    Tile tile;
    GameMap map;

    public Location(Tile tile, GameMap map) {
        this.tile = tile;
        this.map = map;
    }

    public void move(Direction direct) {
        int x = this.getX() + direct.x;
        int y = this.getY() + direct.y;
        tile = map.getTile(x, y);
    }

    public int getDistance(Location other) {
        return Math.abs(this.getX() - other.getX()) + Math.abs(this.getY() - other.getY());
    }

    public boolean isNeighbour(Location location) {
        return map.findNeighbours(tile).contains(location.getTile());
    }

    public int getX() { return tile.getPosX(); }
    public int getY() { return tile.getPosY(); }
    public Tile getTile() { return tile; }
}
