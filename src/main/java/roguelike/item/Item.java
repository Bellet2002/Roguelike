package roguelike.item;

import roguelike.character.Character;

//Item interface defining item behavior
public interface Item {      
    String getName();
    ItemType getType();
    int getLevelRequirement();
    boolean canUse(Character character);
    void use(Character character);
}