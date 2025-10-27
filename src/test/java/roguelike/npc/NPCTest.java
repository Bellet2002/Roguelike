package roguelike.npc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Inventory;
import roguelike.character.Player;
import roguelike.effect.HealingEffect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.Item;
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
        HashSet<Item> loot = new HashSet<>(Set.of(potion, sword));

        FriendlyNPC chest = new FriendlyNPC("Chest", loot, new LootBehavior());

        assertEquals("Chest", chest.getName());
        assertNotNull(chest.getLootItems());
        assertEquals(2, chest.getLootItems().size());
        assertTrue(chest.getLootItems().contains(potion));
        assertTrue(chest.getLootItems().contains(sword));
    }

    @Test
    public void testFriendlyNPCWithShopConstructor() {
        Consumable potion = new Consumable("Potion", ItemType.POTION, 1, new HealingEffect(20));
        HashMap<Item, Integer> shop = new HashMap<>(Map.of(potion, 50));

        FriendlyNPC merchant = new FriendlyNPC("Merchant", shop, new ShopBehavior());

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
        HashSet<Item> loot = new HashSet<>(Set.of(potion, sword));

        FriendlyNPC chest = new FriendlyNPC("Chest", loot, new LootBehavior());

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
        HashMap<Item, Integer> shopItems = new HashMap<>();
        shopItems.put(potion, 50);

        FriendlyNPC merchant = new FriendlyNPC("Merchant", shopItems, new ShopBehavior());

        merchant.interaction(player);

        //assertEquals(10, player.getGold()); // 60 - 50 = 10
        //assertTrue(player.getInventory().containsKey(potion));
    }
}
