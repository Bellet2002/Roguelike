package roguelike;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CharacterTest {
    final int VALID_LEVEL = 1;

    @Test
    public void contructorTest(){
        Character character = new Character("Test", 1000, 3);
        assertEquals(1000, character.getHp());
        assertEquals("Test", character.getName());
        assertEquals(3, character.getLevel() );
    }
}
