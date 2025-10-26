package roguelike.npc;

import roguelike.character.Player;

public interface NPC {
    String getName();
    void interaction(Player player);
}
