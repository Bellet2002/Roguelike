package roguelike.character;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.effect.AttackEffect;
import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Item;
import roguelike.item.ItemType;

public class InventoryTest {

    @Test
    public void itemsCanBeAddedToInventoryTest(){
        Inventory inventory = new Inventory();
        Consumable first = new Consumable("Large Potion", ItemType.POTION, 1, new HealingEffect(20));
        Consumable second = new Consumable("small Potion", ItemType.POTION, 1, new HealingEffect(10));

        ArrayList<Item>items = new ArrayList<>();
        items.add(first);
        items.add(second);

        inventory.addItem(items);

        assertTrue(inventory.getConsumable(first));
        assertTrue(inventory.getConsumable(second));
    }

    @Test
    public void singularItemCanBeAdded(){
        Inventory inventory = new Inventory();
        Consumable item = new Consumable("Large Potion", ItemType.WEAPON, 1, new AttackEffect(20));

        inventory.addItem(item);

    }

    @Test
    public void correctItemIsReturned(){

    }
    
}
