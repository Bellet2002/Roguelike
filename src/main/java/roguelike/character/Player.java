package roguelike.character;

import roguelike.effect.AbstractEffect;
import roguelike.map.Location;

public class Player extends Character{
    private final int MAX_HP;
    

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

    public void applyEffect(AbstractEffect effect){
        
    }
    
    

}
