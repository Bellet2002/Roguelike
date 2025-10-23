package roguelike.character;

public class Character implements Attack{
    final private String name;
    int hp;
    int level;
    private int posX, posY;

    enum Direction{
        RIGHT, LEFT, UP, DOWN
    }

    public Character(String name, int hp, int level){
        this.name = name;
        this.hp = hp;
        if( level > 0) { this.level = level; }
            else { this.level = 1; }

        this.posX = 315;
        this.posY = 315;
    }

    public Character(String name, int hp){
        this(name, hp, 1);
    }

    public String getName() {return name;}

    public int getHp() {return hp;}

    public int getLevel() {return level;}

    public int getXPos() {return posX;}

    public int getYPos(){return posY;}

    void setPosition(int Xpos, int Ypos){
        posX = Xpos;
        posY = Ypos;

        
    }

    @Override
    public void attack(Character target){
        

    }

    //movement in accordance to how position works in javaFX where pos 0,0 is upper left corner
    //and one tile is 70 pixels 
    public void move(Direction direction){

        switch (direction) {
            case UP:
                if (posY >= 70) {
                    this.posY = posY - 70;
                }
                break;
            case DOWN: 
                if (posY <= 630) {
                    this.posY = posY + 70;
                }
                break;
            case LEFT:
                    if (posX >= 70) {
                    this.posX = posX - 70;
                }   
                break;
            case RIGHT:
            if (posX <= 630) {
                    this.posX = posX + 70;
                }
                break;
        }
    }

}
