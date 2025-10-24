package roguelike.character;

import roguelike.item.Weapon;
import roguelike.map.Location;

public abstract class Character {
    final private String name;
    int hp;
    int level;
    private Location location;
    private Weapon weapon;

    public enum Direction{
        UP(0, -1),
        DOWN(0, 1),
        LEFT(-1, 0),
        RIGHT(1, 0);

        public final int x;
        public final int y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public Character(String name, int hp, int level, Location location){
        this.name = name;
        this.hp = hp;
        if( level > 0) { this.level = level; }
            else { this.level = 1; }

        this.location = location;
    }

    public Character(String name, int hp, Location location){
        this(name, hp, 1, location);
    }

    public void attack(Character victim) {
        weapon.attack(victim);
    }

    public String getName() {return name;}

    public int getHp() {return hp;}

    public int getLevel() {return level;}

    public Location getLocation() { return location; }

    public Weapon getWeapon() { return weapon; }

    public void move(Direction direction){
        location.move(direction);
    }
}
