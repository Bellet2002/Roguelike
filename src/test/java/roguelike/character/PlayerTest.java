package roguelike.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import roguelike.character.Character.Direction;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.effect.HealingEffect;
import roguelike.enemy.Enemy;
import roguelike.enemy.enemyBehavior.ChaseBehavior;
import roguelike.enemy.enemyBehavior.EnemyPersonality;
import roguelike.enemy.enemyBehavior.PatrollingBehavior;
import roguelike.item.Consumable;
import roguelike.item.ItemType;
import roguelike.item.WeaponEquipment;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class PlayerTest {
    final int VALID_HP = 500;
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

    @Test
    public void contructorTest(){
        GameMap map = new GameMap(false);
        Player test = new Player("Test", VALID_HP, VALID_LEVEL, new Location(map.getTile(0, 0), map));
        assertEquals(VALID_HP, test.getHp());
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
    public void CharacterCanTakeDamageAndHealTest(){
        GameMap map = new GameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));

        test.takeDamage(100);

        assertEquals(VALID_HP, test.getMaxHp());
        assertEquals(400, test.getHp());

        test.heal(100);
        assertEquals(test.getMaxHp(), test.getHp());
    }

    @Test
    public void CharacterCanDie(){
        GameMap map = new GameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));

        test.takeDamage(VALID_HP);

        assertEquals(0, test.getHp());
        assertFalse(test.isAlive());
    }

    @Test
    public void characterCanAttack(){
        GameMap map = new GameMap(false);
        Player test = new Player("Hero", VALID_HP, new Location(map.getTile(0,0), map));
        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                        new Location(map.getTile(0,0), map),
                        new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));
        test.attack(enemy);

        assertEquals(enemy.getMaxHp() - 10, enemy.getHp());
    }

    @Test
    public void leftMovementTest(){
        GameMap map = new GameMap(false);
        Player chara = new Player("Test", VALID_HP, new Location(map.getTile(1,0), map));

        chara.move(Direction.LEFT);
        assertEquals(0, chara.getLocation().getX());
        assertEquals(0, chara.getLocation().getY());
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
        
        assertEquals(0, test.getLocation().getX());
        assertEquals(map.getHeight()-1, test.getLocation().getY());
    }

    @Test
    public void characterCantMoveLeftOutsideMap(){
        GameMap map = new GameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        test.move(Direction.LEFT);
        
        assertEquals(map.getWidth()-1, test.getLocation().getX());
        assertEquals(0, test.getLocation().getY());
    }

    @Test
    public void playerCanChangeValidWeapon(){
        GameMap map = new GameMap(false);
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
        GameMap map = new GameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        Consumable item = new Consumable("Potion", ItemType.POTION, 1, new DefenseEffect(10));

        test.getInventory().addItem(item);
        test.useItem(item);

        test.addEffect(new AttackEffect(10));

        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                new Location(map.getTile(0,0), map),
                new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));
        test.attack(enemy);
        test.takeDamage(20);

        assertEquals(enemy.getMaxHp()- 20, enemy.getHp()); //10+10
        assertEquals(test.getMaxHp()-10, test.getHp()); //20-10

        test.addEffect(new HealingEffect(5)); //gives back 5 to the 10hp lost

        assertEquals(test.getMaxHp()-5, test.getHp());
    }

    @Test
    public void effectExpires(){
        GameMap map = new GameMap(false);
        Player test = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        Consumable item = new Consumable("Potion", ItemType.POTION, 1, new DefenseEffect(10));

        test.getInventory().addItem(item);
        test.getInventory().getItem("Potion").getEffect().expireEffect();
        test.useItem(item);

        test.takeDamage(20);

        assertEquals(test.getMaxHp()-20, test.getHp());

    }


}
