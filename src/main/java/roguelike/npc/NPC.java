package roguelike.npc;

import roguelike.character.Player;

public interface NPC {
    String getName();
    int getHp();
    void interaction(Player player);
}
