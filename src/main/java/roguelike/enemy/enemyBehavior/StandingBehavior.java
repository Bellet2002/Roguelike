package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public class StandingBehavior implements EnemyBehavior {
    @Override
    public void behavior(Enemy enemy, Player player) {
        if (enemy.getLocation().getDistance(player.getLocation()) <= 20) {
            enemy.speak("Alert!");
        }
    }
}
