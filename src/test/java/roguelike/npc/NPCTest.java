package roguelike.npc;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.Item;
import roguelike.item.ItemType;
import roguelike.npc.FriendlyNPC;
import roguelike.character.Player;
import roguelike.map.GameMap;
import roguelike.map.Location;


public class NPCTest {
    
    @Test
    public void testFriendlyNPCConstructor() {
        FriendlyNPC npc = new FriendlyNPC("Greg");

        assertEquals("Greg", npc.getName());
        assertEquals(100, npc.getHp());
    }

    @Test
    public void testFriendlyNPCWithLootConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new Equipment("Sword", ItemType.WEAPON, 1, 20);
        List<Item> loot = List.of(potion, sword);

        FriendlyNPC chest = new FriendlyNPC("Chest", loot);

        assertEquals("Chest", chest.getName());
        assertEquals(100, chest.getHp());
        assertNotNull(chest.getLootItems());
        assertEquals(2, chest.getLootItems().size());
        assertTrue(chest.getLootItems().contains(potion));
        assertTrue(chest.getLootItems().contains(sword));
    }

    @Test
    public void testFriendlyNPCWithShopConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Map<Item, Integer> shop = Map.of(potion, 50);

        FriendlyNPC merchant = new FriendlyNPC("Merchant", shop);

        assertEquals("Merchant", merchant.getName());
        assertEquals(100, merchant.getHp());
        assertNotNull(merchant.getShopItems());
        assertTrue(merchant.getShopItems().containsKey(potion));
        assertEquals(50, merchant.getShopItems().get(potion));
    }

    /*@Test
    public void testFriendlyNPCGivesLoot() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new Equipment("Sword", ItemType.WEAPON, 1, 10);
        Equipment armor = new Equipment("Armor", ItemType.ARMOR, 1, 15);

        FriendlyNPC chest = new FriendlyNPC("Chest", List.of(potion, sword, armor));
        //Character player = new Player("Player", 3, 100);

        //chest.interaction(player); //player receives loot

        List<Item> inventory = player.getInventory();
        assertTrue(inventory.contains(potion));
        assertTrue(inventory.contains(sword));
        assertTrue(inventory.contains(armor));
        assertEquals(3, inventory.size());
    }

    @Test
    public void testShopPurchaseWorks() {
        GameMap map = new GameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));
        //player.receiveGold(60);

        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Map<Item, Integer> shop = Map.of(potion, 50);

        FriendlyNPC merchant = new FriendlyNPC("Merchant", shop);

       // merchant.interaction(player);
        /*simulate a player buying an item:
        
        Consumable selected = potion;
        int price = merchant.getShopItems().get(selected);

        if (player.getGold() >= price) {
            player.buy(price);
            player.collect(selected);
        }

        assertEquals(10, player.getGold());
        assertTrue(player.getInventory().contains(selected));
    }*/

}
