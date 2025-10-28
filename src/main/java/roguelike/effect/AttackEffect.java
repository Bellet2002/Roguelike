package roguelike.effect;

import roguelike.character.Character;
import roguelike.character.Player;

public class AttackEffect extends AbstractEffect {
    
    public AttackEffect(int amount) {
        super(amount);
    }
    
    @Override
    public void apply(Character character) {
        
        
        if(character instanceof Player player){
            player.addEffect(this);
        }
    }
}
