package roguelike.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import roguelike.character.Character;
import roguelike.character.Player;
import roguelike.effect.HealingEffect;
import roguelike.enemy.Enemy;
import roguelike.enemy.enemybehavior.ChaseBehavior;
import roguelike.enemy.enemybehavior.EnemyPersonality;
import roguelike.enemy.enemybehavior.PatrollingBehavior;
import roguelike.map.GameMap;
import roguelike.map.Location;

public class ItemTest {

    @Test
    public void testEquipmentItemCreation() {
        Equipment weapon = new WeaponEquipment("Great Sword", 1, 10, 3);
        assertEquals("Great Sword", weapon.getName());
        assertEquals(1, weapon.getLevelRequirement());
        assertEquals(ItemType.WEAPON, weapon.getType());
        assertEquals(10, weapon.getStat());
        assertEquals(3, weapon.getDurability());
    }

    @Test
    public void testConsumableItemCreation() {
        HealingEffect healingEffect = new HealingEffect(20);
        Consumable potion = new Consumable("Healing Potion", ItemType.POTION, 1, healingEffect);
        assertEquals("Healing Potion", potion.getName());
        assertEquals(1, potion.getLevelRequirement());
        assertEquals(ItemType.POTION, potion.getType());
        assertEquals(healingEffect, potion.getEffect());
    }

    @Test
    public void testConsumableWithHealingEffectHealsCorrectly() {
        GameMap map = GameMap.createGameMap(false);
        Character player = new Player("Player", 100, 3, new Location(map.getTile(0, 0), map));
        player.takeDamage(30); //100 - 30 = 70

        Consumable potion = new Consumable("Healing Potion", ItemType.POTION, 1, new HealingEffect(20));
        potion.use(player); //applies HealingEffect -> 70 + 20 = 90
        assertEquals(90, player.getHp());
    }

    @Test
    public void testConsumableIsEmptyWhenUsed() {
        GameMap map = GameMap.createGameMap(false);
        Character player = new Player("Player", 100, 3, new Location(map.getTile(0, 0), map));
        player.takeDamage(30); //100 - 30 = 70

        HealingEffect healingEffect = new HealingEffect(20);
        Consumable potion = new Consumable("Healing Potion", ItemType.POTION, 1, healingEffect);

        potion.use(player);
        assertEquals(90, player.getHp());
        potion.use(player);
        assertEquals(90, player.getHp()); //empty potion does not heal
    }

    @Test
    public void testItemCannotBeUsedBelowLevelRequirement() {
        GameMap map = GameMap.createGameMap(false);
        Character player = new Player("Player", 100, 1, new Location(map.getTile(0, 0), map));

        AbstractItem potion = new Consumable("Healing Potion", ItemType.POTION, 5, new HealingEffect(20));
        assertFalse(potion.canUse(player));
    }

    @Test
    public void testItemCanBeUsedExactlyAtLevelRequirement() {
        GameMap map = GameMap.createGameMap(false);
        Character player = new Player("Player", 100, 5, new Location(map.getTile(0, 0), map));

        AbstractItem armor = new Equipment("Armor", ItemType.ARMOR, 5, 10, 10);
        assertTrue(armor.canUse(player));
    }

    @Test
    public void testItemCanBeUsedAboveLevelRequirement() {
        GameMap map = GameMap.createGameMap(false);
        Character player = new Player("Player", 100, 6, new Location(map.getTile(0, 0), map));

        AbstractItem armor = new Equipment("Armor", ItemType.ARMOR, 5, 10, 10);
        assertTrue(armor.canUse(player));
    }

    @Test
    public void testWeaponAttackReducesEnemyHp() {
        Player player = new Player("Hero", 100, 5, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 1, 30, 3);
        GameMap map = GameMap.createGameMap(false);
        Enemy goblin = new Enemy(
                            "Goblin",
                            100,
                            2,
                            new Location(map.getTile(0, 0), map),
                            new EnemyPersonality(
                                new PatrollingBehavior(map),
                                new ChaseBehavior()
                            )
        );

        sword.attack(goblin, player);

        assertEquals(70, goblin.getHp());
    }

    @Test
    public void testWeaponDurabilityDecreasesOnAttack() {
        Player player = new Player("Hero", 100, 5, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 1, 10, 3);
        GameMap map = GameMap.createGameMap(false);
        Enemy goblin = new Enemy(
                "Goblin",
                50,
                1,
                new Location(map.getTile(0, 0), map),
                new EnemyPersonality(
                    new PatrollingBehavior(map),
                    new ChaseBehavior()
                )
        );

        assertEquals(3, sword.getDurability());
        sword.attack(goblin, player);
        assertEquals(2, sword.getDurability());
        sword.attack(goblin, player);
        assertEquals(1, sword.getDurability());
    }

    @Test
    public void testWeaponDurabilityIsNotNegativeAfterAttack() {
        Player player = new Player("Hero", 100, 5, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 1, 10, 0); 
        GameMap map = new GameMap(false);
        Enemy goblin = new Enemy(
                "Goblin",
                50,
                1,
                new Location(map.getTile(0, 0), map),
                new EnemyPersonality(
                    new PatrollingBehavior(map),
                    new ChaseBehavior()
                )
        );

        sword.attack(goblin, player);
        assertEquals(0, sword.getDurability());
    }

    @Test
    public void testWeaponIsBroken() {
        WeaponEquipment sword = new WeaponEquipment("Sword", 1, 10, 0); 

        assertTrue(sword.isBroken());
    }

    @Test
    public void testPlayerAboveLevelWithBrokenSword() {
        Player player = new Player("Hero", 100, 5, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 1, 10, 0);

        assertFalse(sword.canUse(player));
    }

    @Test
    public void testPlayerUnderLevelWithNotBrokenSword() {
        Player player = new Player("Hero", 100, 1, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 5, 10, 3);

        assertFalse(sword.canUse(player));
    }

    @Test
    public void testPlayerCanUseWeapon() {
        Player player = new Player("Hero", 100, 5, null);
        WeaponEquipment sword = new WeaponEquipment("Sword", 5, 10, 3);

        assertTrue(sword.canUse(player));
    }
}
