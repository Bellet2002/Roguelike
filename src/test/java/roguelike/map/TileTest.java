package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TileTest {
    @Test
    void testCorrectSymbolAndType() {
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
        tile = new Tile(null, 0, 0);
        assertEquals("?", tile.getSymbol());
    }

    @Test
    void testCorrectWeight() {
        Tile tile = new Tile(TileType.GRASS, 0, 0);
        assertEquals(3, tile.getWeight());
        tile = new Tile(TileType.WATER, 0, 0);
        assertEquals(10, tile.getWeight());
        tile = new Tile(null, 0, 0);
        assertEquals(3, tile.getWeight());

    }

    @Test
    void testSymbolAndWeightIsUpdated() {
        Tile tile = new Tile(TileType.ROAD, 0, 0);
        assertEquals(TileType.ROAD, tile.getType());
        assertEquals("R", tile.getSymbol());
        assertEquals(1, tile.getWeight());
        tile.setType(TileType.MOUNTAIN);
        assertEquals(TileType.MOUNTAIN, tile.getType());
        assertEquals("M", tile.getSymbol());
        assertEquals(6, tile.getWeight());
    }

    @Test
    void testEquals() {
        Tile tile = new Tile(TileType.GRASS, 0, 0);
        Tile other = new Tile(TileType.FOREST, 0, 0);
        Object fake = new Object();
        assertTrue(tile.equals(tile));
        assertFalse(tile.equals(fake));
        assertFalse(tile.equals(other));
        other.setType(TileType.GRASS);
        assertTrue(tile.equals(other));
    }
}
