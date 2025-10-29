package roguelike.character;

import java.util.ArrayList;

import roguelike.effect.AbstractEffect;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.effect.HealingEffect;
import roguelike.enemy.Enemy;
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
    public void attack(Enemy enemy, Player player){
        //should check the effect for attacking to eventually boost attack
        int baseDamage = this.getWeapon().getStat();
        int addedDmg = 0;
        for (AbstractEffect effect : effects) {
            if(effect instanceof AttackEffect atkEffect && !atkEffect.isExpired()){
                addedDmg += atkEffect.getAmount();
            } 
        }

        int actualDamage = baseDamage + addedDmg;

        if(actualDamage<baseDamage){
            for(AbstractEffect effect: effects){
                if(effect instanceof DefenseEffect defenseEffect && defenseEffect.getAmount()>0){
                    defenseEffect.expireEffect();
                    actualDamage = baseDamage;
                    break;
                }
            }
        }

        enemy.takeDamage(actualDamage);

        clearExpiredEffects();
    }

    @Override
    public void takeDamage(int damage){
        if(playerHasImmunity()){
            return;
        }

        int reducedDmg = 0;
        for (AbstractEffect effect : effects) {
            if(effect instanceof DefenseEffect dfnEffect && !dfnEffect.isExpired()){
                reducedDmg += dfnEffect.getAmount();
            } 
        }
        int actualDamage = Math.max(0, damage - reducedDmg);

        if(actualDamage>damage){
            for(AbstractEffect effect: effects){
                if(effect instanceof HealingEffect healing && healing.getAmount()>0){
                    healing.expireEffect();
                    actualDamage = damage;
                    break;
                }else if (effect instanceof AttackEffect attackEffect && attackEffect.getAmount()>0){
                    attackEffect.expireEffect();
                    actualDamage = damage;
                    break;
                }
            }
        }
        super.takeDamage(actualDamage);

        clearExpiredEffects();
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
            if(w == getWeapon()) return;
            if(inventory.equipmentExists(w)){
                inventory.addItem(getWeapon());
                inventory.use(w);
                super.setWeapon(weapon);
            }  
        }
    }

    public void useItem(Consumable item){
        if(!inventory.consumableExists(item)) return;

        if(item.getEffect() instanceof HealingEffect effect){
            item.use(this);
            if(effect.getAmount()<= 0) inventory.use(item);
            return;
        }

        item.use(this);
        inventory.use(item);

    }

    public void addEffect(AbstractEffect effect) {
        if(effect instanceof HealingEffect healing){
            healing.apply(this);
        }
        effects.add(effect);
    }

    private void clearExpiredEffects(){
        effects.removeIf(AbstractEffect::isExpired);
    }

    private boolean playerHasImmunity(){
        HealingEffect healingToUse = null;
        DefenseEffect defenceToUse = null;

        for (AbstractEffect e: effects){
            if (healingToUse == null && e instanceof HealingEffect h && h.getAmount() > 0) {
                healingToUse = h;
            } else if (defenceToUse == null && e instanceof DefenseEffect d && d.getAmount() > 0) {
                defenceToUse = d;
            }
            //To skip unnecesary looping
            if (healingToUse != null && defenceToUse != null) break;
        }
        if(healingToUse != null && defenceToUse != null){
            healingToUse.expireEffect();
            defenceToUse.expireEffect();
            clearExpiredEffects();
            return true;
        }

        return false;

    }

}
