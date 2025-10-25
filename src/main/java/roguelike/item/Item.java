package roguelike.item;

import roguelike.character.Player;

//Item interface defining item behavior
public interface Item {      
    String getName();
    ItemType getType();
    int getLevelRequirement();
    boolean canUse(Player player);
    void use(Player player);
}