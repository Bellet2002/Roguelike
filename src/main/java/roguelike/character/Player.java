package roguelike.character;

import java.util.ArrayList;

import roguelike.effect.AbstractEffect;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.item.Consumable;
import roguelike.item.Weapon;
import roguelike.item.WeaponEquipment;
import roguelike.map.Location;

public class Player extends Character{
    private ArrayList<AbstractEffect> effects = new ArrayList<>();
    private Inventory inventory = new Inventory();
    

    public Player(String name, int hp, int level, Location location) {
        super(name, hp, level, location);
        WeaponEquipment beginnerSword = new WeaponEquipment("Starter Sword", 1, 10, 100);
        super.setWeapon(beginnerSword);
        inventory.addItem(beginnerSword);
    }
    public Player(String name, int hp, Location location){
        super(name, hp, location);
        WeaponEquipment beginnerSword = new WeaponEquipment("Starter Sword", 1, 10, 100);
        super.setWeapon(beginnerSword);
        inventory.addItem(beginnerSword);
    }

    void levelUp(){
        level++;
    }

    @Override
    public void attack(Character enemy){
        //should check the effect for attacking to eventually boost attack
        int baseDamage = this.getWeapon().getStat();
        int addedDmg = 0;
        for (AbstractEffect effect : effects) {
            if(effect instanceof AttackEffect atkEffect && !atkEffect.isExpired()){
                addedDmg += atkEffect.getAmount();
            } 
        }
        enemy.setHp(baseDamage+addedDmg);
    }

    @Override
    public void setHp(int damage){
        int reducedDmg = 0;
        for (AbstractEffect effect : effects) {
            if(effect instanceof DefenseEffect dfnEffect && !dfnEffect.isExpired()){
                reducedDmg += dfnEffect.getAmount();
            } 
        }
        super.setHp(damage-reducedDmg);
    }

    public Inventory getInventory(){ return inventory; }

    @Override
    public WeaponEquipment getWeapon(){
        return (WeaponEquipment)super.getWeapon();
    }

    /* When you set a new weapon your used weapon gets put back in inventory
     * while the weapon you plan to use gets "taken out" from the inventory
     * aka you cannot use a weapon that is not in your inventory.
    */
    @Override
    public void setWeapon(Weapon weapon){
        if(weapon instanceof WeaponEquipment w){
            if(inventory.EquipmentExists(w)){
                inventory.addItem((WeaponEquipment)getWeapon());
                inventory.use(w);
                super.setWeapon(weapon);
            }  
        }
    }

    public void useItem(Consumable item){
        if(inventory.ConsumableExists(item)){
            item.use(this);
        }
    }

    public void addEffect(AbstractEffect effect) {
        effects.add(effect);
    }

}
