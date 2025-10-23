package roguelike.npc;

import java.util.List;

import roguelike.character.Character;
import roguelike.item.Item;

public class HostileNPC extends CreateNPC {
    private final int attackDamage;
    private final int level;
    private final List<Item> dropItems;

    public HostileNPC(String name, int hp, int attackDamage, int level, List<Item> dropItems) {
        super(name, hp, true);
        this.attackDamage = attackDamage;
        this.level = level;
        this.dropItems = dropItems;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public int getLevel() {
        return level;
    }

    public List<Item> getDropItems() { 
        return dropItems; 
    }

    @Override
    public void interaction(Character character) {
        //NPC defeated
        if (character.getLevel() >= level) { 
            //character.collect(dropItems);
        }
        //NPC attacks
    }
}
