package roguelike.npc;

import roguelike.character.Character;

public interface NPC {
    String getName();
    int getHp();
    boolean isHostile();
    void interaction(Character character);
}
