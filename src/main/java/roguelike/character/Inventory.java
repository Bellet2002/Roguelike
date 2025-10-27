package roguelike.character;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import roguelike.item.Consumable;
import roguelike.item.Equipment;
import roguelike.item.Item;

public class Inventory {
    private HashMap<Consumable, Integer> consumable = new HashMap<>();
    private HashMap<Equipment, Integer> equipment = new HashMap<>();

    public void addItem(HashSet<Item> items){
        for(Item i : items){
            if(i instanceof Consumable item){
                addItem(item);
            }
            if(i instanceof Equipment item){
                addItem(item);
            }
        }
    }

    public void addItem(Consumable item){
        if(consumable.containsKey(item)){
                    consumable.put(item, consumable.get(item)+1);
                }
                consumable.put(item, 1);
    }

    public void addItem(Equipment item){
        if(equipment.containsKey(item) && isnotNull(item)){
            equipment.put(item, equipment.get(item)+1);
            }
        equipment.put(item, 1);
    }

    public boolean ConsumableExists(Consumable item){
        return consumable.containsKey(item);
    }

    public boolean EquipmentExists(Equipment item){
        return equipment.containsKey(item);
    }

    public void use(Consumable item){
        if(ConsumableExists(item) && isnotNull(item)){
            consumable.remove(item);
        }
    }

    public void use(Equipment item){
        if( EquipmentExists(item) && isnotNull(item)){
            equipment.remove(item);
        }
    }

    private boolean isnotNull(Object o){
        return o != null;
    }
    
}
