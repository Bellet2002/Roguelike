package roguelike.effect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.map.GameMap;
import roguelike.map.Location;
import roguelike.character.Player;

public class EffectTest {

    @Test
    public void testValidHealing() {
        GameMap map = new GameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);
        player.takeDamage(50); //simulates player taking damage, setHP = hp - damage

        healing.apply(player); //applies HealingEffect -> character.heal(20)
        assertEquals(0, healing.getAmount());
        assertEquals(70, player.getHp());
    }

    @Test
    public void testHealingExceedsHp() {
        GameMap map = new GameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);
        player.takeDamage(5); //hp = 95

        healing.apply(player);
        assertEquals(15, healing.getAmount()); //partially used
        assertEquals(100, player.getHp());
    }

    @Test
    public void testInvalidHealingAmount() {
        GameMap map = new GameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        player.takeDamage(5); //hp = 95

        new HealingEffect(0).apply(player);
        new HealingEffect(-10).apply(player);
        
        assertEquals(95, player.getHp()); 
    }

    @Test
    public void testHealingWithFullHp() {
        GameMap map = new GameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        HealingEffect healing = new HealingEffect(20);

        healing.apply(player);
        assertEquals(20, healing.getAmount()); //healing unused, stays the same
        assertEquals(100, player.getHp());
    }
}
