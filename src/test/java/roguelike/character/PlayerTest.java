package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.character.Character.Direction;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class PlayerTest {
    final int VALID_HP = 1500;
    final int VALID_LEVEL = 10;

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
        assertEquals(VALID_LEVEL + 1, test.getLevel());
    }

    public void contructorTest(){
        GameMap map = new GameMap(false);
        Player test = new Player("Test", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        assertEquals(1000, test.getHp());
        assertEquals("Test", test.getName());
        assertEquals(VALID_LEVEL, test.getLevel() );
    }

    @Test
    public void CharacterStartingPositionTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        assertEquals(0, chara.getLocation().getX());
        assertEquals(0, chara.getLocation().getY());

    }

    @Test
    public void leftMovementTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(1,0), map));

        chara.move(Direction.LEFT);
        assertEquals(0, chara.getLocation().getX());
    }

    @Test
    public void rightMovementTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        chara.move(Direction.RIGHT);
        assertEquals(1, chara.getLocation().getX());
    }

    @Test
    public void upMovementTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,1), map));

        chara.move(Direction.UP);
        assertEquals(0,chara.getLocation().getY());
    }

    @Test
    public void downMovementTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        chara.move(Direction.DOWN);
        assertEquals(1, chara.getLocation().getY());

    }

    @Test
    public void characterCantMoveUpOutsideMap(){
        GameMap map = new GameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        test.move(Direction.UP);
        
        assertEquals(0, test.getLocation().getY());
    }

    @Test
    public void characterCantMoveLeftOutsideMap(){
        GameMap map = new GameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        test.move(Direction.LEFT);
        
        assertEquals(0, test.getLocation().getX());
    }
}
