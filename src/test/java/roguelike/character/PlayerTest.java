package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.character.Character.Direction;
import roguelike.enemy.Enemy;
import roguelike.enemy.enemybehavior.ChaseBehavior;
import roguelike.enemy.enemybehavior.EnemyPersonality;
import roguelike.enemy.enemybehavior.PatrollingBehavior;
import roguelike.item.WeaponEquipment;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class PlayerTest {
    final int VALID_HP = 500;
    final int VALID_LEVEL = 10;

    @Test
    public void playerShouldInheritCharacterAttributes() {
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        assertEquals("Hero", test.getName());
        assertEquals(VALID_HP, test.getHp());
        assertEquals(VALID_LEVEL, test.getLevel());
    }

    @Test
    public void playerCanLevelUp(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Hero", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        test.levelUp();
        assertEquals(VALID_LEVEL + 1, test.getLevel());
    }

    @Test
    public void contructorTest(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Test", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        assertEquals(VALID_HP, test.getHp());
        assertEquals("Test", test.getName());
        assertEquals(VALID_LEVEL, test.getLevel() );
    }

    @Test
    public void CharacterStartingPositionTest(){
        GameMap map = GameMap.createGameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        assertEquals(0, chara.getLocation().getX());
        assertEquals(0, chara.getLocation().getY());
    }

    @Test
    public void CharacterCanTakeDamageTest(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));

        test.takeDamage(100);

        assertEquals(VALID_HP, test.getMaxHp());
        assertEquals(400, test.getHp());
    }

    @Test
    public void CharacterCanDie(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));

        test.takeDamage(VALID_HP);

        assertEquals(0, test.getHp());
        assertEquals(false, test.isAlive());
    }

    @Test
    public void characterCanAttack(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));
        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                        new Location(map.getTile(0,0), map),
                        new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));
        test.attack(enemy, test);

        assertEquals(enemy.getMaxHp() - 10, enemy.getHp());
    }

    @Test
    public void characterCanHeal(){

    }

    @Test
    public void leftMovementTest(){
        GameMap map = GameMap.createGameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(1,0), map));

        chara.move(Direction.LEFT);
        assertEquals(0, chara.getLocation().getX());
        assertEquals(0, chara.getLocation().getY());
    }

    @Test
    public void rightMovementTest(){
        GameMap map = GameMap.createGameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        chara.move(Direction.RIGHT);
        assertEquals(1, chara.getLocation().getX());
    }

    @Test
    public void upMovementTest(){
        GameMap map = GameMap.createGameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,1), map));

        chara.move(Direction.UP);
        assertEquals(0,chara.getLocation().getY());
    }

    @Test
    public void downMovementTest(){
        GameMap map = GameMap.createGameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(0,0), map));

        chara.move(Direction.DOWN);
        assertEquals(1, chara.getLocation().getY());

    }

    @Test
    public void characterCantMoveUpOutsideMap(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        test.move(Direction.UP);
        
        assertEquals(0, test.getLocation().getX());
        assertEquals(map.getHeight()-1, test.getLocation().getY());
    }

    @Test
    public void characterCantMoveLeftOutsideMap(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        test.move(Direction.LEFT);
        
        assertEquals(map.getWidth()-1, test.getLocation().getX());
        assertEquals(0, test.getLocation().getY());
    }

    @Test
    public void playerCanChangeValidWeapon(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map)); 
        
        WeaponEquipment sword = new WeaponEquipment("Better Sword", 1 ,10 ,100);
        WeaponEquipment outside = new WeaponEquipment("NotInInventorySword", 1 ,10 ,100);

        test.getInventory().addItem(sword);
        test.setWeapon(sword);
        test.setWeapon(outside);

        assertEquals(sword, test.getWeapon());
    }

    @Test
    public void usingEffectAddsToPlayer(){
        GameMap map = GameMap.createGameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map)); 

    }

    @Test
    public void effectExpires(){

    }


}
