package roguelike.npc.npcBehavior;

import roguelike.character.Player;
import roguelike.npc.FriendlyNPC;

public interface NPCBehavior {
    void interact(FriendlyNPC npc, Player player);
}
