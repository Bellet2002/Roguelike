package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.character.Character.Direction;


public class CharacterTest {
    final int VALID_LEVEL = 1;
    final int VALID_HP = 1000;
    final int START_POS= 315;

    //Byta Detta till Player test kanske? Går inte att testa abstrakta klasser direkt
    /*@Test
    public void contructorTest(){
        Character character = new Character("Test", VALID_HP, VALID_LEVEL);
        assertEquals(1000, character.getHp());
        assertEquals("Test", character.getName());
        assertEquals(VALID_LEVEL, character.getLevel() );
    }

    @Test
    public void CharacterStartingPositionTest(){
        Character chara = new Character("Test", VALID_HP);

        assertEquals(START_POS, chara.getXPos());
        assertEquals(START_POS, chara.getYPos());
    }

    @Test
    public void leftMovementTest(){
        Character chara = new Character("Test", VALID_HP);

        chara.move(Direction.LEFT);
        assertEquals(START_POS - 70, chara.getXPos());
    }

    @Test
    public void rightMovementTest(){
        Character chara = new Character("Test", VALID_HP);

        chara.move(Direction.RIGHT);
        assertEquals(START_POS + 70, chara.getXPos());
    }

    @Test
    public void upMovementTest(){
        Character chara = new Character("Test", VALID_HP);

        chara.move(Direction.UP);
        assertEquals(START_POS - 70, chara.getYPos());
    }

    @Test
    public void downMovementTest(){
        Character chara = new Character("Test", VALID_HP);

        chara.move(Direction.DOWN);
        assertEquals(START_POS + 70, chara.getYPos());

    }

    @Test
    public void characterCantMoveUpOutsideMap(){
        Character test = new Character("name", VALID_HP);
        test.setPosition(0, 0);
        test.move(Direction.UP);
        
        assertEquals(0, test.getYPos());
    }

    @Test
    public void characterCantMoveLeftOutsideMap(){
        Character test = new Character("name", VALID_HP);
        test.setPosition(0, 0);
        test.move(Direction.LEFT);
        
        assertEquals(0, test.getXPos());
    } */
    

}
