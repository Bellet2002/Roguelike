package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    int VALID_HP = 1500;
    int VALID_LEVEL = 10;

    @Test
    public void playerShouldInheritCharacterAttributes() {
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL);
        assertEquals("Hero", test.getName());
        assertEquals(VALID_HP, test.getHp());
        assertEquals(VALID_LEVEL, test.getLevel());
    }

    @Test
    public void playerCanLevelUp(){
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL);
        test.levelUp();
        assertEquals(VALID_LEVEL + 1, test.level);
    }
    
}
