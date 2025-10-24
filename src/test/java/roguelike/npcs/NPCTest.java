package roguelike.npcs;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Character;
import roguelike.character.Player;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.effect.HealingEffect;
import roguelike.item.CreateItem;
import roguelike.item.Item;
import roguelike.item.ItemType;
import roguelike.npc.NonHostileNPC;


public class NPCTest {
    
    @Test
    public void testNonHostileNPCDefaultConstructor() {
        NonHostileNPC npc = new NonHostileNPC("Greg");

        assertEquals("Greg", npc.getName());
        assertEquals(100, npc.getHp());
        assertFalse(npc.isHostile());
    }

    @Test
    public void testNonHostileNPCWithLootConstructor() {
        Item potion = new CreateItem("Potion", 20, ItemType.POTION, 1, new HealingEffect());
        Item sword = new CreateItem("Sword", 10, ItemType.WEAPON, 1, new AttackEffect());
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
        Item potion = new CreateItem("Potion", 20, ItemType.POTION, 1, new HealingEffect());
        Map<Item, Integer> shop = Map.of(potion, 50);

        NonHostileNPC merchant = new NonHostileNPC("Merchant", shop);

        assertEquals("Merchant", merchant.getName());
        assertEquals(100, merchant.getHp());
        assertFalse(merchant.isHostile());
        assertNotNull(merchant.getShopItems());
        assertTrue(merchant.getShopItems().containsKey(potion));
        assertEquals(50, merchant.getShopItems().get(potion));
    }

    @Test
    public void testNonHostileNPCGivesLoot() {
        Item potion = new CreateItem("Potion", 20, ItemType.POTION, 1, new HealingEffect());
        Item sword = new CreateItem("Sword", 10, ItemType.WEAPON, 1, new AttackEffect());
        Item armor = new CreateItem("Armor", 5, ItemType.ARMOR, 1, new DefenseEffect());

        NonHostileNPC chest = new NonHostileNPC("Chest", List.of(potion, sword, armor));
        //Character player = new Player("Player", 3, 100);

        //chest.interaction(player); //player receives loot

        /*List<Item> inventory = player.getInventory(); (requires that character has inventory)
        assertTrue(inventory.contains(potion));
        assertTrue(inventory.contains(sword));
        assertTrue(inventory.contains(armor));
        assertEquals(3, inventory.size());*/
    }

    @Test
    public void testShopPurchaseWorks() {
        //Character player = new Player("Player", 1, 100);
        //player.addGold(60); (character needs gold)

        Item potion = new CreateItem("Potion", 20, ItemType.POTION, 1, new HealingEffect());
        Map<Item, Integer> shop = Map.of(potion, 50);

        NonHostileNPC merchant = new NonHostileNPC("Merchant", shop);

       // merchant.interaction(player);
        /*simulate a player buying an item:
        
        Item selected = potion;
        int price = merchant.getShopItems().get(selected);

        if (player.getGold() >= price) {
            player.buy(price);
            player.collect(selected);
        }

        assertEquals(10, player.getGold());
        assertTrue(player.getInventory().contains(selected));*/
    }

}
