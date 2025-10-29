package roguelike.npc;

import java.util.HashMap;
import java.util.HashSet;

import roguelike.character.Player;
import roguelike.item.Item;
import roguelike.npc.npcBehavior.NPCBehavior;

//Concrete class for friendly NPCs
public class FriendlyNPC extends AbstractNPC {
    private final HashMap<Item, Integer> shopItems;
    private final HashSet<Item> lootItems;
    private final NPCBehavior behavior;

    //Constructor for default NPCs
    public FriendlyNPC(String name, NPCBehavior behavior) {
        super(name);
        this.shopItems = null;
        this.lootItems = null;
        this.behavior = behavior;
    }

    //Constructor for NPCs that give loot
    public FriendlyNPC(String name, HashSet<Item> lootItems, NPCBehavior behavior) {
        super(name);
        this.shopItems = null;
        this.lootItems = lootItems;
        this.behavior = behavior;
    }

    //Constructor for NPCs carrying shop items
    public FriendlyNPC(String name, HashMap<Item, Integer> shopItems, NPCBehavior behavior) {
        super(name);
        this.shopItems = shopItems;
        this.lootItems = null;
        this.behavior = behavior;
    }

    public void addLootItem(Item item) {
        if (lootItems != null) {
            lootItems.add(item);
        }
    }

    public void addShopItem(Item item, int price) {
        if (shopItems != null) {
            shopItems.put(item, price);
        }
    }

    public void removeItem(Item item) {
        if (lootItems != null) {
            lootItems.remove(item);
        } else if (shopItems != null) {
            shopItems.remove(item);  
        }
    }

    public HashSet<Item> getLootItems() {
        return lootItems;
    }

    public HashMap<Item, Integer> getShopItems() {
        return shopItems;
    }

    @Override
    public void interaction(Player player) {
        if (behavior != null) {
            behavior.interact(this, player);
        }
    }
}
