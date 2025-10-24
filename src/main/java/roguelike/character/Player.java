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
        level++;
    }



    
    

}
