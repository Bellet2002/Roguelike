package roguelike.character;

import org.junit.jupiter.api.Test;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.enemy.Enemy;
import roguelike.enemy.enemyBehavior.ChaseBehavior;
import roguelike.enemy.enemyBehavior.EnemyPersonality;
import roguelike.enemy.enemyBehavior.PatrollingBehavior;
import roguelike.item.Consumable;
import roguelike.item.ItemType;
import roguelike.map.GameMap;
import roguelike.map.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModifiedEffectOnPlayerTest {
    final int VALID_HP = 100;
    final int VALID_LEVEL = 10;

    @Test
    public void baseLineTest(){
        GameMap map = new GameMap(false);
        Player player = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                new Location(map.getTile(0,0), map),
                new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));

        //Nothing applied
        player.attack(enemy);
        assertEquals(enemy.getMaxHp()-10, enemy.getHp());

        player.takeDamage(10);
        assertEquals(player.getMaxHp()-10, player.getHp());
    }

    @Test
    public void decreasedAttackTest(){ //WIP
        GameMap map = new GameMap(false);
        Player player = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                new Location(map.getTile(0,0), map),
                new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));

        player.addEffect(new AttackEffect(10));
    }

    @Test
    public void decreasedDefenceTest(){ //WIP
        GameMap map = new GameMap(false);
        Player player = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        Enemy enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                new Location(map.getTile(0,0), map),
                new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));

        player.addEffect(new AttackEffect(10));
    }

    @Test
    public void healingAndDefenceImmunityTest(){}

    @Test
    public void healingCancelsOutDefenceDebuffTest(){}

    @Test
    public void buffedAttackDefenceAndHealingRaiseAttackTest(){}

    @Test
    public void deBuffedAttackAndDefenceTakeMoreDamageTest(){}






}
