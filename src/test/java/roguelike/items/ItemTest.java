package roguelike.items;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import roguelike.character.Character;
import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.ItemType;

public class ItemTest {

    @Test
    public void testEquipmentItemCreation() {
        Equipment weapon = new Equipment("Great Sword", ItemType.WEAPON, 1, 10);
        assertEquals("Great Sword", weapon.getName());
        assertEquals(1, weapon.getLevelRequirement());
        assertEquals(ItemType.WEAPON, weapon.getType());
        assertEquals(10, weapon.getStat());
    }

    @Test
    public void testConsumableItemCreation() {
        HealingEffect healingEffect = new HealingEffect(20);
        Consumable potion = new Consumable("Large Potion", ItemType.POTION, 1, healingEffect);
        assertEquals("Large Potion", potion.getName());
        assertEquals(1, potion.getLevelRequirement());
        assertEquals(ItemType.POTION, potion.getType());
        assertEquals(healingEffect, potion.getEffect());
    }

    @Test
    public void potionHealsCorrectAmount() {
        Character player = new Character("Player", 70, 3);
        HealingEffect healingEffect = new HealingEffect(20);
        Consumable potion = new Consumable("Healing Potion", ItemType.POTION, 1, healingEffect);
        potion.use(player); //applies HealingEffect
        assertEquals(90, player.getHp());
    }
}
