package roguelike.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class MapTest {
    private int width = 15, height = 10;
    @Test
    void mapIsCorrectSize() {
        Map map = new Map(width, height);
        List<List<Tile>> list = map.getMap();
        assertEquals(list.size(), height);
        assertEquals(list.get(0).size(), width);
    }

    @Test
    void terrainIsGenerated() {
        Map map = new Map(width, height);

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
}
