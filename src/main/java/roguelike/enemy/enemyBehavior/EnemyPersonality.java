package roguelike.enemy.enemyBehavior;

import roguelike.character.Player;
import roguelike.enemy.Enemy;

public class EnemyPersonality implements EnemyBehavior {
    private final EnemyBehavior active;
    private final EnemyBehavior idle;
    private boolean visiblePlayer = false;

    public EnemyPersonality(EnemyBehavior active, EnemyBehavior idle) {
        this.active = active;
        this.idle = idle;
    }

    @Override
    public void behavior(Enemy enemy, Player player) {
        visiblePlayer = (enemy.getLocation().getDistance(player.getLocation()) <= 10) ? true : false;
        if (visiblePlayer) {
            active.behavior(enemy, player);
        } else {
            idle.behavior(enemy, player);
        }
    }

    public EnemyBehavior getAggressive() { return active; }
    public EnemyBehavior getIdle() { return idle; }
}
