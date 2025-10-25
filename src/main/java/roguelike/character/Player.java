package roguelike.character;

import java.util.ArrayList;

import roguelike.effect.Effect;
import roguelike.item.Consumable;
import roguelike.map.Location;

public class Player extends Character{
    private final int MAX_HP;
    private ArrayList<Effect> effects = new ArrayList<>();
    private Inventory inventory = new Inventory();
    

    public Player(String name, int hp, int level, Location location) {
        super(name, hp, level, location);
        MAX_HP = hp;
    }
    public Player(String name, int hp, Location location){
        super(name, hp, location);
        MAX_HP = hp;
    }

    void levelUp(){
        level++;
    }

    @Override
    public void attack(Character enemy){
        
    }

    @Override
    public void move(Direction dir){

    }

    public int getMaxHp(){
        return MAX_HP;
    }

    public void use(Consumable item){
        item.getEffect();

    }
    
    

}
