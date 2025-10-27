package roguelike.character;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.Item;
import roguelike.item.ItemType;

public class InventoryTest {

    @Test
    public void itemsCanBeAddedToInventoryTest(){
        Inventory inventory = new Inventory();
        Consumable first = new Consumable("Large Potion", ItemType.POTION, 1, new HealingEffect(20));
        Consumable second = new Consumable("small Potion", ItemType.POTION, 1, new HealingEffect(10));
        Equipment weapon = new Equipment("Great Sword", ItemType.WEAPON, 1, 10);
        HashSet<Item> items = new HashSet<>();
        items.add(first);
        items.add(second);
        items.add(weapon);

        inventory.addItem(items);

        assertTrue(inventory.ConsumableExists(first));
        assertTrue(inventory.ConsumableExists(second));
        assertTrue(inventory.EquipmentExists(weapon));

    }

    @Test
    public void singularItemCanBeAdded(){
        Inventory inventory = new Inventory();
        Equipment item = new Equipment("Great Sword", ItemType.WEAPON, 1, 10);

        inventory.addItem(item);

        assertTrue(inventory.EquipmentExists(item));

    }

    @Test
    public void itemIsGoneFromInventoryAfterUseTest(){
        Inventory inventory = new Inventory();
        Equipment sword = new Equipment("Test Sword", ItemType.WEAPON, 1, 10);
        Consumable potion = new Consumable("small Potion", ItemType.POTION, 1, new HealingEffect(10));

        inventory.addItem(potion);
        inventory.addItem(sword);

        inventory.use(potion);

        assertFalse(inventory.ConsumableExists(potion));
    }
    
}
