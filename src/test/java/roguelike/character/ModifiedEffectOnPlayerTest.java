package roguelike.character;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import roguelike.effect.AttackEffect;
import roguelike.effect.DefenseEffect;
import roguelike.effect.HealingEffect;
import roguelike.enemy.Enemy;
import roguelike.enemy.enemybehavior.ChaseBehavior;
import roguelike.enemy.enemybehavior.EnemyPersonality;
import roguelike.enemy.enemybehavior.PatrollingBehavior;
import roguelike.item.Consumable;
import roguelike.item.ItemType;
import roguelike.map.GameMap;
import roguelike.map.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ModifiedEffectOnPlayerTest {
    public static final int NEGATIVE_EFFECT = -5;
    public static final int DAMAGE = 50;
    public static final int POSITIVE_EFFECT = 10;
    final int VALID_HP = 100;
    final int VALID_LEVEL = 10;

    Player player;
    Enemy enemy;

    @BeforeEach
    public void setUp(){
        GameMap map = GameMap.createGameMap(false);
        player = new Player("name", VALID_HP, new Location(map.getTile(0,0), map));
        enemy = new Enemy("Enemy", VALID_HP, VALID_LEVEL,
                new Location(map.getTile(0,0), map),
                new EnemyPersonality(new PatrollingBehavior(map),
                        new ChaseBehavior()));
    }

    @Test
    public void baseLineTest(){
        //Nothing applied
        player.attack(enemy, player);
        assertEquals(enemy.getMaxHp()-10, enemy.getHp());

        player.takeDamage(10);
        assertEquals(player.getMaxHp()-10, player.getHp());
    }

    @Test
    @DisplayName("While healingItem has value it should be able to be used")
    public void healingCanBeUsedSeveralTimes(){
        player.takeDamage(DAMAGE);
        Consumable potion = new Consumable("Healing", ItemType.POTION, 1, new HealingEffect(55));
        player.getInventory().addItem(potion);

        player.useItem(potion);
        assertEquals(player.getMaxHp(), player.getHp());

        //The potion can heal 55 hp but only 50 was used 55-50
        assertEquals(5,player.getInventory().getItem("Healing").getEffect().getAmount());

        player.takeDamage(DAMAGE);
        player.useItem(potion);

        assertEquals(player.getMaxHp() - DAMAGE + 5, player.getHp());
        assertFalse(player.getInventory().consumableExists(potion));
    }

    @Test
    public void decreasedAttackTest(){
        player.addEffect(new AttackEffect(NEGATIVE_EFFECT));
        player.attack(enemy, player);

        assertEquals(enemy.getMaxHp()- 5, enemy.getHp()); //player attack - NEGATIVE-EFFECT

    }

    @Test
    public void decreasedDefenceTest(){
        player.addEffect(new DefenseEffect(NEGATIVE_EFFECT));
        player.takeDamage(DAMAGE);

        assertEquals(player.getMaxHp()-DAMAGE+NEGATIVE_EFFECT, player.getHp());

    }

    @Test
    public void healingAndDefenceImmunityTest(){
        player.addEffect(new HealingEffect(POSITIVE_EFFECT));
        player.addEffect(new DefenseEffect(POSITIVE_EFFECT));

        player.takeDamage(DAMAGE);
        assertEquals(player.getMaxHp(), player.getHp());

        player.takeDamage(DAMAGE);
        assertEquals(player.getMaxHp() - DAMAGE, player.getHp());

    }

    @Test
    public void healingCancelsOutDefenceDebuffTest(){
        player.addEffect(new HealingEffect(POSITIVE_EFFECT));
        player.addEffect(new DefenseEffect(NEGATIVE_EFFECT));

        player.takeDamage(DAMAGE);

        assertEquals(player.getMaxHp() - DAMAGE, player.getHp());
    }

    @Test
    @DisplayName("buff/debuff cancels out")
    public void buffedAttackCancelsOutDebuffedDefenceAndViceversa(){
        player.addEffect(new AttackEffect(POSITIVE_EFFECT));
        player.addEffect(new DefenseEffect(POSITIVE_EFFECT));
        Consumable atkPotion = new Consumable("Attack Potion", ItemType.POTION, 1, new AttackEffect(POSITIVE_EFFECT));
        player.getInventory().addItem(atkPotion);
        player.useItem(atkPotion);
    }

    @Test
    public void defenceBuffAndAttackBuffTest(){
        Consumable item = new Consumable("Potion", ItemType.POTION, 1, new DefenseEffect(POSITIVE_EFFECT));

        player.getInventory().addItem(item);
        player.useItem(item);
        player.addEffect(new AttackEffect(POSITIVE_EFFECT));

        player.attack(enemy, player);
        player.takeDamage(DAMAGE);

        assertEquals(enemy.getMaxHp()- 20, enemy.getHp()); //baseAttack = 10, POSITIVE_EFFECT = 10
        assertEquals(player.getMaxHp()-(DAMAGE-POSITIVE_EFFECT), player.getHp());
    }

    @Test
    public void DefenceTest(){}






}
