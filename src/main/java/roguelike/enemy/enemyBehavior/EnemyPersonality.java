package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public class EnemyPersonality implements EnemyBehavior {
    private final EnemyBehavior aggressive;
    private final EnemyBehavior idle;
    private boolean visiblePlayer = false;

    public EnemyPersonality(EnemyBehavior aggressive, EnemyBehavior idle) {
        this.aggressive = aggressive;
        this.idle = idle;
    }

    @Override
    public void behavior(Enemy enemy, Player player) {
        visiblePlayer = enemy.getLocation().getDistance(player.getLocation()) < 4;

        if (visiblePlayer) {
            aggressive.behavior(enemy, player);
        } else {
            idle.behavior(enemy, player);
        }
    }
}
