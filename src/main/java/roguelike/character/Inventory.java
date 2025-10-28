package roguelike.character;

import java.util.HashMap;
import java.util.HashSet;

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
            } else if(i instanceof Equipment item){
                addItem(item);
            }
        }
    }

    public void addItem(Consumable item){
        consumable.put(item, consumable.getOrDefault(item,0)+1);
    }

    public void addItem(Equipment item){
        equipment.put(item, equipment.getOrDefault(item,0)+1);

    }

    public boolean consumableExists(Consumable item){
        return consumable.containsKey(item);
    }

    public boolean equipmentExists(Equipment item){
        return equipment.containsKey(item);
    }

    public void use(Consumable item){
        if(consumableExists(item) && isnotNull(item)){
            consumable.remove(item);
        }
    }

    public void use(Equipment item){
        if( equipmentExists(item) && isnotNull(item)){
            equipment.remove(item);
        }
    }

    public Consumable getItem(String name){
        for (Consumable item: consumable.keySet()){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    private boolean isnotNull(Object o){
        return o != null;
    }
    
}
