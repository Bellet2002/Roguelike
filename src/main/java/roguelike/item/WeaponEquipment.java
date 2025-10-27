package roguelike.item;

import roguelike.character.Character;
import roguelike.character.Player;


public class WeaponEquipment extends Equipment implements Weapon {

    public WeaponEquipment(String name, int levelRequirement, int attackPower, int durability) {
        super(name, ItemType.WEAPON, levelRequirement, attackPower, durability); 
    }

    @Override
    public void attack(Character target, Player player) {
        if (canUse(player)) {
            target.takeDamage(getStat());
            durability--; 
        }
    }
}
