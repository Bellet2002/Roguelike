package roguelike.item;

import roguelike.Character;

//Item interface defining item behavior
public interface Item {      
    String getName();
    int getPower();
    ItemType getType();
    int getLevelRequirement();
    boolean canUse(Character character);
    void use(Character character);
}