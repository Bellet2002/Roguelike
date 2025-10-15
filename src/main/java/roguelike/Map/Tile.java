package roguelike.map;

import java.util.Objects;

public class Tile {
    private TileType type;
    private String symbol;
    private int weight;
    private int posX, posY;

    public Tile(TileType type, int posX, int posY) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        updateSymbol();
        updateWeight();
        
    }

    private void updateSymbol() {
        symbol = (type == TileType.GRASS) ? "G" :
            (type == TileType.WATER) ? "W" :
            (type == TileType.FOREST) ? "F" :
            (type == TileType.MOUNTAIN) ? "M" : 
            (type == TileType.ROAD) ? "R" : "?";
    }

    private void updateWeight() {
        weight = (type == TileType.GRASS) ? 3 :
            (type == TileType.WATER) ? 10 :
            (type == TileType.FOREST) ? 5 :
            (type == TileType.MOUNTAIN) ? 6 : 3;
    }

    public void setType(TileType type) { 
        this.type = type;
        updateSymbol();
        updateWeight();
    }
    public TileType getType() { return this.type; }
    public String getSymbol() { return this.symbol; }
    public int getWeight() { return this.weight; }
    public int getPosX() { return this.posX; }
    public int getPosY() { return this.posY; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile other)) return false;
        return posX == other.posX && posY == other.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }
}