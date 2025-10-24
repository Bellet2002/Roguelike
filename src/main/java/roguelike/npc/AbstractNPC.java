package roguelike.npc;

import roguelike.character.Character;

//Abstract base class for NPCs
public abstract class AbstractNPC implements NPC {
    private final String name;
    private final int hp;


    public AbstractNPC(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public abstract void interaction(Character character);
}
