package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    void correctSymbolAndType() {
        Tile tile = new Tile(TileType.WATER);
        assertEquals(TileType.WATER, tile.getType());
        assertEquals("W", tile.getSymbol());
        tile = new Tile(TileType.FOREST);
        assertEquals(TileType.FOREST, tile.getType());
        assertEquals("F", tile.getSymbol());
        tile = new Tile(TileType.GRASS);
        assertEquals(TileType.GRASS, tile.getType());
        assertEquals("G", tile.getSymbol());
        tile = new Tile(TileType.MOUNTAIN);
        assertEquals(TileType.MOUNTAIN, tile.getType());
        assertEquals("M", tile.getSymbol());
    }
}
