package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.map.GameMap;
import roguelike.map.Location;

public class PlayerTest {
    int VALID_HP = 1500;
    int VALID_LEVEL = 10;

    @Test
    public void playerShouldInheritCharacterAttributes() {
        GameMap map = new GameMap(false);
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        assertEquals("Hero", test.getName());
        assertEquals(VALID_HP, test.getHp());
        assertEquals(VALID_LEVEL, test.getLevel());
    }

    @Test
    public void playerCanLevelUp(){
        GameMap map = new GameMap(false);
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        test.levelUp();
        assertEquals(VALID_LEVEL + 1, test.level);
    }
    
}
