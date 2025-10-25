package roguelike.npc;

import java.util.List;
import java.util.Map;

import roguelike.character.Player;
import roguelike.item.Item;

//Concrete class for non-hostile NPCs
public class FriendlyNPC extends AbstractNPC {
    private final Map<Item, Integer> shopItems; //null if not a merchant
    private final List<Item> lootItems;

    //Constructor for default NPCs
    public FriendlyNPC(String name) {
        super(name, 100);
        this.shopItems = null;
        this.lootItems = null;
    }

    //Constructor for NPCs that give loot (like chests, quests etc.)
    public FriendlyNPC(String name, List<Item> lootItems) {
        super(name, 100);
        this.shopItems = null;
        this.lootItems = lootItems;
    }

    //Constructor for NPCs carrying shop items
    public FriendlyNPC(String name, Map<Item, Integer> shopItems) {
        super(name, 100);
        this.shopItems = shopItems;
        this.lootItems = null;
    }

    public Map<Item, Integer> getShopItems() {
        return shopItems;
    }

    public List<Item> getLootItems() {
        return lootItems;
    }

    @Override
    public void interaction(Player player) {
        if (shopItems != null && !shopItems.isEmpty()) {
            //Shop logic
        }

        //NPC gives loot items
        if (lootItems != null && !lootItems.isEmpty()) {
            for (Item item : lootItems) {
                if (item.canUse(player)) {
                    //character.collect(item);
                }
            }
        }

    }
}
