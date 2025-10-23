package roguelike.npc;

import roguelike.character.Character;

//Abstract base class for NPCs
public abstract class CreateNPC implements NPC {
    private final String name;
    private final int hp;
    private final boolean isHostile;


    public CreateNPC(String name, int hp, boolean isHostile) {
        this.name = name;
        this.hp = hp;
        this.isHostile = isHostile;
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
    public boolean isHostile() {
        return isHostile;
    }

    @Override
    public abstract void interaction(Character character);
}
