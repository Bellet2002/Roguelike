package roguelike.npcs;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Character;
import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.Item;
import roguelike.item.ItemType;
import roguelike.npc.HostileNPC;
import roguelike.npc.NonHostileNPC;


public class NPCTest {
    
    @Test
    public void testHostileNPC() {
        HostileNPC monster = new HostileNPC("Monster", 500, 10, 5, null);

        assertEquals("Monster", monster.getName());
        assertEquals(500, monster.getHp());
        assertTrue(monster.isHostile());
        assertEquals(10, monster.getAttackDamage());
        assertEquals(5, monster.getLevel());
    }

    @Test
    public void testHostileNPCDropsItems() {
        Equipment sword = new Equipment("Sword", ItemType.WEAPON, 1, 10);
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        List<Item> drops = List.of(sword, potion);

        HostileNPC monster = new HostileNPC("Monster", 500, 10, 5, drops);

        List<Item> droppedItems = monster.getDropItems();
        assertNotNull(droppedItems, "Monster should drop items");
        assertEquals(2, droppedItems.size());
        assertEquals("Sword", droppedItems.get(0).getName());
        assertEquals("Potion", droppedItems.get(1).getName());
    }
    
    @Test
    public void testNonHostileNPCDefaultConstructor() {
        NonHostileNPC npc = new NonHostileNPC("Greg");

        assertEquals("Greg", npc.getName());
        assertEquals(100, npc.getHp());
        assertFalse(npc.isHostile());
    }

    @Test
    public void testNonHostileNPCWithLootConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new Equipment("Sword", ItemType.WEAPON, 1, 20);
        List<Item> loot = List.of(potion, sword);

        NonHostileNPC chest = new NonHostileNPC("Chest", loot);

        assertEquals("Chest", chest.getName());
        assertEquals(100, chest.getHp());
        assertFalse(chest.isHostile());
        assertNotNull(chest.getLootItems());
        assertEquals(2, chest.getLootItems().size());
        assertTrue(chest.getLootItems().contains(potion));
        assertTrue(chest.getLootItems().contains(sword));
    }

    @Test
    public void testNonHostileNPCWithShopConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Map<Item, Integer> shop = Map.of(potion, 50);

        NonHostileNPC merchant = new NonHostileNPC("Merchant", shop);

        assertEquals("Merchant", merchant.getName());
        assertEquals(100, merchant.getHp());
        assertFalse(merchant.isHostile());
        assertNotNull(merchant.getShopItems());
        assertTrue(merchant.getShopItems().containsKey(potion));
        assertEquals(50, merchant.getShopItems().get(potion));
    }

    /*@Test
    public void testNonHostileNPCGivesLoot() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new Equipment("Sword", ItemType.WEAPON, 1, 10);
        Equipment armor = new Equipment("Armor", ItemType.ARMOR, 1, 15);

        NonHostileNPC chest = new NonHostileNPC("Chest", List.of(potion, sword, armor));
        Character player = new Character("Player", 3, 100);

        chest.interaction(player); //player receives loot

        List<Item> inventory = player.getInventory(); (requires that character has inventory)
        assertTrue(inventory.contains(potion));
        assertTrue(inventory.contains(sword));
        assertTrue(inventory.contains(armor));
        assertEquals(3, inventory.size());
    }*/

    @Test
    public void testShopPurchaseWorks() {
        Character player = new Character("Player", 100, 1);
        //player.receiveGold(60);

        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Map<Item, Integer> shop = Map.of(potion, 50);

        NonHostileNPC merchant = new NonHostileNPC("Merchant", shop);

        merchant.interaction(player);
        /*simulate a player buying an item:
        
        Consumable selected = potion;
        int price = merchant.getShopItems().get(selected);

        if (player.getGold() >= price) {
            player.buy(price);
            player.collect(selected);
        }

        assertEquals(10, player.getGold());
        assertTrue(player.getInventory().contains(selected));*/
    }

}
