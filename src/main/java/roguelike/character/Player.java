package roguelike.character;

import roguelike.map.Location;

public class Player extends Character{
    

    public Player(String name, int hp, int level, Location location) {
        super(name, hp, level, location);
    }
    public Player(String name, int hp, Location location){
        super(name, hp, location);
    }

    void levelUp(){
        this.level ++;
    }

    //come up with something more interesting, or read how others do it somewhere
    int getBaseAttack(){
        return this.hp/100;
    }
    

}
