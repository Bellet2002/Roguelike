package roguelike.effect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Player;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class EffectTest {
    //HealingEffect tests (equivalence class)
    @Test
    public void testNewHealingEffect() {
        HealingEffect healing = new HealingEffect(20);

        assertEquals(20, healing.getAmount());
        assertFalse(healing.isEmpty());
        assertFalse(healing.isExpired());
    }

    @Test
    public void testExpiredHealingEffect() {
        HealingEffect healing = new HealingEffect(20);

        assertFalse(healing.isExpired());
        healing.expireEffect();
        assertTrue(healing.isExpired());
    }

    @Test
    public void testHealingIsEmpty() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(50);
        
        HealingEffect healing = new HealingEffect(20);

        assertFalse(healing.isEmpty());
        healing.apply(player);
        assertTrue(healing.isEmpty());
    }

    @Test //TC1
    public void testValidHealing() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(50); //simulates player taking damage, setHP = hp - damage
        
        HealingEffect healing = new HealingEffect(20);
        assertFalse(healing.isExpired());

        healing.apply(player); //applies HealingEffect -> character.heal(20)
        assertTrue(healing.isEmpty());
        assertEquals(0, healing.getAmount());
        assertEquals(70, player.getHp());
    }

    @Test //TC2
    public void testHealingHealsExactlyToMaxHp() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(20);
        
        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(0, healing.getAmount());
        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test //TC3
    public void testHealingExceedsHp() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(5); //hp = 95

        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(15, healing.getAmount()); //partially used
        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test //TC4
    public void testHealingEdgeCaseMinimum() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(99); //1 hp

        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(0, healing.getAmount());
        assertEquals(21, player.getHp());
    }

    @Test //TC5
    public void testHealingWithFullHp() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        
        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(20, healing.getAmount()); //healing unused, stays the same
        assertEquals(player.getMaxHp(), player.getHp());
    }

    @Test //TC6
    public void testEmptyHealingEffectDoesNothing() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(20);

        HealingEffect healing = new HealingEffect(0);
        
        healing.apply(player);
        assertEquals(80, player.getHp());
    }

    @Test //TC7
    public void testInvalidHealingAmount() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(20);

        HealingEffect healing = new HealingEffect(-10);

        healing.apply(player);
        assertEquals(80, player.getHp());
    }

    @Test //TC8
    public void testHealingDoesNotHealDeadPlayer() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);

        assertTrue(player.isAlive());

        player.takeDamage(100); 
        assertFalse(player.isAlive()); //dead
        
        healing.apply(player);
        assertFalse(player.isAlive()); //still dead
        assertEquals(20, healing.getAmount()); //healing unused
        assertEquals(0, player.getHp()); //player not healed
    }

    @Test //TC10
    public void testExpiredHealingEffectDoesNotHeal() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);
        player.takeDamage(5); //hp = 95

        assertFalse(healing.isExpired());

        healing.apply(player);
        assertEquals(15, healing.getAmount());
        assertEquals(player.getMaxHp(), player.getHp());

        player.takeDamage(10); //hp = 90
        healing.expireEffect();

        healing.apply(player);
        assertTrue(healing.isExpired());
        assertEquals(15, healing.getAmount()); //healing unused, stays the same
        assertEquals(90, player.getHp()); //player not healed
    }

    //AttackEffect tests
    @Test
    public void testAttackEffect() {
        AttackEffect attack = new AttackEffect(20);

        assertEquals(20, attack.getAmount());
        assertFalse(attack.isExpired());
    }

    @Test
    public void testExpiredAttackEffect() {
        AttackEffect attack = new AttackEffect(20);

        attack.expireEffect();
        assertTrue(attack.isExpired());
    }

    /*@Test
    public void testAttackEffectIsApplied() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        AttackEffect attack = new AttackEffect(20);

        attack.apply(player);
        //AssertEquals som på något sätt testar att player.attack() får högre attackDmg effter applicerad effekt
    }*/

    //DefenseEffect tests
    @Test
    public void testDefenseEffect() {
        DefenseEffect defense = new DefenseEffect(20);

        assertEquals(20, defense.getAmount());
        assertFalse(defense.isExpired());
    }

    @Test
    public void testExpiredDefenseEffect() {
        DefenseEffect defense = new DefenseEffect(20);

        defense.expireEffect();
        assertTrue(defense.isExpired());
    }

    /*@Test
    public void testDefenseEffectIsApplied() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));

        //AssertEquals som på något sätt testar att defense adderas efter applicerad effekt
    }*/

}
