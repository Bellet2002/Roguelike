package roguelike.item;

import roguelike.character.Character;


public class WeaponEquipment extends Equipment implements Weapon {
    private int durability;

    public WeaponEquipment(String name, int levelRequirement, int attackPower, int durability) {
        super(name, ItemType.WEAPON, levelRequirement, attackPower);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = Math.max(0, durability);
    }

    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public void attack(Character target) {
        if (!isBroken()) {
            target.setHp(getStat());
            durability--; 
        }
    }
}
