package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public class ChaseBehavior implements EnemyBehavior {
    @Override
    public void behavior(Enemy enemy, Player player) {
        if (enemy.getLocation().getDistance(player.getLocation()) <= 1) {
            enemy.attack(player);
            }
        else {
            enemy.moveTowards(player.getLocation());
            enemy.attack(player);
        }
    }
}
