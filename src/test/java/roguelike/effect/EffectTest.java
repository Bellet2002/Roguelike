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
    public void testHealingEffect() {
        HealingEffect healing = new HealingEffect(20);

        assertEquals(20, healing.getAmount());
        assertFalse(healing.isExpired());
    }

    @Test
    public void testExpiredHealingEffect() {
        HealingEffect healing = new HealingEffect(20);

        healing.expireEffect();
        assertTrue(healing.isExpired());
    }

    @Test
    public void testValidHealing() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);
        player.takeDamage(50); //simulates player taking damage, setHP = hp - damage

        healing.apply(player); //applies HealingEffect -> character.heal(20)
        assertEquals(0, healing.getAmount());
        assertEquals(70, player.getHp());
    }

    @Test
    public void testHealingExceedsHp() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);
        player.takeDamage(5); //hp = 95

        healing.apply(player);
        assertEquals(15, healing.getAmount()); //partially used
        assertEquals(100, player.getHp());
    }

    @Test
    public void testInvalidHealingAmount() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(5); //hp = 95

        new HealingEffect(0).apply(player);
        new HealingEffect(-10).apply(player);
        
        assertEquals(95, player.getHp()); 
    }

    @Test
    public void testHealingWithFullHp() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(20, healing.getAmount()); //healing unused, stays the same
        assertEquals(100, player.getHp());
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

        //Defense finns inte men ifall vi antar att
        //defense.apply(player); player def = def + defense.getAmount();
        //AssertEquals som på något sätt testar att defense adderas efter applicerad effekt
    }*/

}
