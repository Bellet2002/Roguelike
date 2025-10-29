package roguelike.npc;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Inventory;
import roguelike.character.Player;
import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.ItemType;
import roguelike.item.WeaponEquipment;
import roguelike.map.GameMap;
import roguelike.map.Location;
import roguelike.npc.npcBehavior.LootBehavior;
import roguelike.npc.npcBehavior.ShopBehavior;


public class NPCTest {
    
    @Test
    public void testFriendlyNPCConstructor() {
        FriendlyNPC npc = new FriendlyNPC("Greg", null);

        assertEquals("Greg", npc.getName());
    }

    @Test
    public void testFriendlyNPCWithLootConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new WeaponEquipment("Sword", 1, 20, 3);
        
        FriendlyNPC chest = new FriendlyNPC("Chest", new HashSet<>(), new LootBehavior());
        chest.addLootItem(potion);
        chest.addLootItem(sword);

        assertEquals("Chest", chest.getName());
        assertNotNull(chest.getLootItems());
        assertEquals(2, chest.getLootItems().size());
        assertTrue(chest.getLootItems().contains(potion));
        assertTrue(chest.getLootItems().contains(sword));
    }

    @Test
    public void testFriendlyNPCWithShopConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC merchant = new FriendlyNPC("Merchant", new HashMap<>(), new ShopBehavior());
        merchant.addShopItem(potion, 50);

        assertEquals("Merchant", merchant.getName());
        assertNotNull(merchant.getShopItems());
        assertTrue(merchant.getShopItems().containsKey(potion));
        assertEquals(50, merchant.getShopItems().get(potion));
    }

    @Test
    public void testLootNPCBehaviorGivesLoot() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));

        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        Equipment sword = new WeaponEquipment("Sword", 1, 20, 10);
        
        FriendlyNPC chest = new FriendlyNPC("Chest", new HashSet<>(), new LootBehavior());
        chest.addLootItem(potion);
        chest.addLootItem(sword);

        chest.interaction(player);

        Inventory inventory = player.getInventory();
        assertNotNull(inventory);
        assertTrue(inventory.consumableExists(potion));
        assertTrue(inventory.equipmentExists(sword));
    }

    @Test
    public void testShopBehaviorPurchaseItem() {
        GameMap map = GameMap.createGameMap(false);
        Player player = new Player("Hero", 100, 1, new Location(map.getTile(0, 0), map));

        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC merchant = new FriendlyNPC("Merchant", new HashMap<>(), new ShopBehavior());
        merchant.addShopItem(potion, 50);

        merchant.interaction(player);

        //assertEquals(10, player.getGold()); // 60 - 50 = 10
        //assertTrue(player.getInventory().containsKey(potion));
    }

    @Test
    public void testRemoveLootItem() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC chest = new FriendlyNPC("Chest", new HashSet<>(), new LootBehavior());
        chest.addLootItem(potion);

        assertTrue(chest.getLootItems().contains(potion));
        chest.removeItem(potion);
        assertFalse(chest.getLootItems().contains(potion));
    }

    @Test
    public void testRemoveShopItem() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC merchant = new FriendlyNPC("Merchant", new HashMap<>(), new ShopBehavior());
        merchant.addShopItem(potion, 50);

        assertTrue(merchant.getShopItems().containsKey(potion));
        merchant.removeItem(potion);
        assertFalse(merchant.getShopItems().containsKey(potion));
    }

    @Test
    public void testAddLootItemToNormalNPCDoesNothing() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC npc = new FriendlyNPC("Greg", null);
        npc.addLootItem(potion); 

        assertNull(npc.getLootItems());
    }

    @Test
    public void testAddShopItemToNormalNPCDoesNothing() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        
        FriendlyNPC npc = new FriendlyNPC("Greg", null);
        npc.addShopItem(potion, 50); 

        assertNull(npc.getShopItems());
    }
}
