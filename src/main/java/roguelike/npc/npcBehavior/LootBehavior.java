package roguelike.npc.npcBehavior;

import java.util.HashSet;

import roguelike.character.Player;
import roguelike.item.Item;
import roguelike.npc.FriendlyNPC;

public class LootBehavior implements NPCBehavior {
    @Override
    public void interact(FriendlyNPC npc, Player player) {
        HashSet<Item> loot = npc.getLootItems();
        if (loot != null) {
            player.getInventory().addItem(loot);
        }
    }
}
