package roguelike.character;

import java.util.ArrayList;

import roguelike.effect.Effect;
import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.ItemType;
import roguelike.map.Location;

public class Player extends Character{
    private ArrayList<Effect> effects = new ArrayList<>();
    private Inventory inventory = new Inventory();
    

    public Player(String name, int hp, int level, Location location) {
        super(name, hp, level, location);
        //super.setWeapon(new Equipment("Starter Sword", ItemType.WEAPON, 1, 10));
    }
    public Player(String name, int hp, Location location){
        super(name, hp, location);
    }

    void levelUp(){
        level++;
    }

    @Override
    public void attack(Character enemy){
        
    }

    
    

}
