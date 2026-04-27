package Managers;//import Targets.Targets.DevilTarget;

import Aiming.GameState;
import Targets.Target;
import Targets.DevilTarget;
import Targets.EnemyTarget;
import Targets.FriendlyTarget;

import java.util.*;

public class TargetManager {

    private List<Target> targets = new ArrayList<>();
    private int maxTargets = 20;
    private Random rand = new Random();

    public void spawnRandom(GameState state) {

        if (targets.size() < maxTargets)
        {
            Target t;

            if (state == GameState.PRACTICE) {
                t = new FriendlyTarget();
            } else {
                int r = rand.nextInt(3);
                if (r == 0) t = new EnemyTarget();
                else if (r == 1) t = new FriendlyTarget();
                else t = new DevilTarget();
            }
            double x, y;
            int attempts = 0;

            do {
                x = rand.nextInt(700);
                y = rand.nextInt(500);
                attempts++;
            } while (isOverlapping(x, y, 90) && attempts < 20);

            t.spawn(x,y);

            targets.add(t);
        }


    }

    public boolean isOverlapping(double x, double y, double radius) {

        for (Target t : targets) {
            double tx = t.getX() + t.getWidth() / 2.0;
            double ty = t.getY() + t.getHeight() / 2.0;

            double dx = x - tx;
            double dy = y - ty;

            double distance = Math.sqrt(dx * dx + dy * dy);

            if (distance < radius * 2) {
                return true;
            }

        }
        return false;
    }

    public void handleClick(double x, double y, GameManager game) {

        Iterator<Target> it = targets.iterator();

        while (it.hasNext()) {
            Target t = it.next();

            if (t.isHit(x, y)) {
                t.onHit(game);
                t.destroy();
                it.remove();
                return;
            }
        }
    }

    public void clear() {
        for (Target t : targets) {
            t.destroy();
        }
        targets.clear();
    }
}
