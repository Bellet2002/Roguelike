package roguelike.character;

public class Player extends Character{
    

    Player(String name, int hp, int level) {
        super(name, hp, level);
    }
    Player(String name, int hp){
        super(name, hp);
    }

    void levelUp(){
        this.level ++;
    }

    //come up with something more interesting, or read how others do it somewhere
    int getBaseAttack(){
        return this.hp/100;
    }
    

}
