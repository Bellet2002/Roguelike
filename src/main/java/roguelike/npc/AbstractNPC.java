package roguelike.npc;

import roguelike.character.Player;

//Abstract base class for NPCs
public abstract class AbstractNPC implements NPC {
    private final String name;


    public AbstractNPC(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public abstract void interaction(Player player);
}
