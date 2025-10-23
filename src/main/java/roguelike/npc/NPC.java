package roguelike.npc;

import roguelike.Character;

public interface NPC {
    String getName();
    int getHp();
    boolean isHostile();
    void interaction(Character character);
}
