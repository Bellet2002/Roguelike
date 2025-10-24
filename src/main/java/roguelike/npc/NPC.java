package roguelike.npc;

import roguelike.character.Character;

public interface NPC {
    String getName();
    int getHp();
    void interaction(Character character);
}
