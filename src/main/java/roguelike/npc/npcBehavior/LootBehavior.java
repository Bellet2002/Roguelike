package roguelike.npc.npcBehavior;

import roguelike.character.Player;
import roguelike.npc.FriendlyNPC;
import roguelike.item.Item;

public class LootBehavior implements NPCBehavior {
    @Override
    public void interact(FriendlyNPC npc, Player player) {
        if (npc.getLootItems() != null) {
            for (Item item : npc.getLootItems()) {
                //player.collect(item);
            }
        }
    }
}
