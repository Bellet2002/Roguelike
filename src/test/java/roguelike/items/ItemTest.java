package roguelike.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.character.Character;
import roguelike.character.Player;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.effect.HealingEffect;
import roguelike.item.CreateItem;
import roguelike.item.Item;
import roguelike.item.ItemType;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class ItemTest {

    @Test
    public void testWeaponItemCreation() {
        Item weapon = new CreateItem("Great Sword", 10, ItemType.WEAPON, 1, new AttackEffect());
        assertEquals("Great Sword", weapon.getName());
        assertEquals(10, weapon.getPower());
        assertEquals(1, weapon.getLevelRequirement());
        assertEquals(ItemType.WEAPON, weapon.getType());
    }

    @Test
    public void testArmorItemCreation() {
        Item armor = new CreateItem("Leather Armor", 5, ItemType.ARMOR, 1, new DefenseEffect());
        assertEquals("Leather Armor", armor.getName());
        assertEquals(5, armor.getPower());
        assertEquals(1, armor.getLevelRequirement());
        assertEquals(ItemType.ARMOR, armor.getType());
    }

    @Test
    public void testPotionItemCreation() {
        Item potion = new CreateItem("Large Potion", 20, ItemType.POTION, 1, new HealingEffect());
        assertEquals("Large Potion", potion.getName());
        assertEquals(20, potion.getPower());
        assertEquals(1, potion.getLevelRequirement());
        assertEquals(ItemType.POTION, potion.getType());
    }

    @Test
    public void potionHealsCorrectAmount() {
        GameMap map = new GameMap(false);
        Character player = new Player("Player", 3, 70, new Location(map.getTile(0, 0), map));

        Item potion = new CreateItem("Healing Potion", 20, ItemType.POTION, 1, new HealingEffect());
        potion.use(player); //applies HealingEffect

        assertEquals(90, player.getHp());
    }
}
