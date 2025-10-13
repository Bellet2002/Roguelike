package roguelike.Map;

public class Tile {
    private TileType type;
    private String symbol;

    public Tile(TileType type) {
        this.type = type;

        symbol = (type == TileType.GRASS) ? "G" :
                 (type == TileType.WATER) ? "W" :
                 (type == TileType.FOREST) ? "F" :
                 (type == TileType.MOUNTAIN) ? "M" : "?";
    }

    public String getSymbol() { return this.symbol; }
}