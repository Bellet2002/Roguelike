package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    void correctSymbolAndType() {
        Tile tile = new Tile(TileType.WATER, 0, 0);
        assertEquals(TileType.WATER, tile.getType());
        assertEquals("W", tile.getSymbol());
        tile = new Tile(TileType.FOREST, 0, 0);
        assertEquals(TileType.FOREST, tile.getType());
        assertEquals("F", tile.getSymbol());
        tile = new Tile(TileType.GRASS, 0, 0);
        assertEquals(TileType.GRASS, tile.getType());
        assertEquals("G", tile.getSymbol());
        tile = new Tile(TileType.MOUNTAIN, 0, 0);
        assertEquals(TileType.MOUNTAIN, tile.getType());
        assertEquals("M", tile.getSymbol());
    }

    @Test
    void correctWeight() {
        Tile tile = new Tile(TileType.GRASS, 0, 0);
        assertEquals(3, tile.getWeight());
        tile = new Tile(TileType.WATER, 0, 0);
        assertEquals(10, tile.getWeight());
    }

    @Test
    void symbolAndWeightIsUpdated() {
        Tile tile = new Tile(TileType.GRASS, 0, 0);
        assertEquals(TileType.GRASS, tile.getType());
        assertEquals("G", tile.getSymbol());
        assertEquals(3, tile.getWeight());
        tile.setType(TileType.MOUNTAIN);
        assertEquals(TileType.MOUNTAIN, tile.getType());
        assertEquals("M", tile.getSymbol());
        assertEquals(6, tile.getWeight());
    }
}
