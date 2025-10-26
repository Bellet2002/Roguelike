package roguelike.npc.npcBehavior;

import roguelike.character.Player;
import roguelike.npc.FriendlyNPC;

public class ShopBehavior implements NPCBehavior {
    @Override
    public void interact(FriendlyNPC npc, Player player) {
        if (npc.getShopItems() != null) {
            
        }
    }
}
