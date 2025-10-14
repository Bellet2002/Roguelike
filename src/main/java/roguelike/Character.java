package roguelike;

public class Character {
    private String name;
    private int hp;
    private int level;

    public Character(String name, int hp, int level){
        this.name = name;
        this.hp = hp;
        this.level = level;
    }

    public Character(String name, int hp){
        this(name, hp, 1);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getLevel() {
        return level;
    }
}
