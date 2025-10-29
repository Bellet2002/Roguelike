package roguelike.character;

import roguelike.enemy.Enemy;
import roguelike.item.Weapon;
import roguelike.map.Location;

public abstract class Character {
    private final String name;
    private final int maxHp;
    private int hp;
    private int level;
    private Location location;
    private Weapon weapon;
    private boolean isAlive = true;

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
        this.maxHp = hp;
        if( level > 0) { this.level = level; }
            else { this.level = 1; }

        this.location = location;
    }

    public Character(String name, int hp, Location location){
        this(name, hp, 1, location);
    }

    public void attack(Enemy victim, Player attacker) {
        if(weapon != null) {
            weapon.attack(victim, attacker);
        }
    }

    public void takeDamage(int damage) {
        if ((hp - damage) <= 0) {
            hp = 0;
            isAlive = false;
        } else {
            hp -= damage;
        }
    }

    public void heal(int amount) {
        if (amount <= 0 || hp == maxHp) {
            return;
        } 

        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }   

    public String getName() {return name;}

    public int getHp() {return hp;}

    public int getMaxHp() {return maxHp;}

    public int getLevel() {return level;}

    public Location getLocation() { return location; }

    public Weapon getWeapon() { return weapon; }

    public void setWeapon(Weapon weapon){ this.weapon = weapon;}

    public boolean isAlive() { return isAlive; }

    public void setLevel(int newLevel) { this.level = newLevel; }

    public void move(Direction direction){
        location.move(direction);
    }
}
