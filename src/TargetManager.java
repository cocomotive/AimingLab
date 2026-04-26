import java.util.*;

public class TargetManager {

    private List<Target> targets = new ArrayList<>();
    private Random rand = new Random();

    public void spawnRandom(GameState state) {

        Target t;

        if (state == GameState.PRACTICE) {
            t = new FriendlyTarget();
        } else {
            int r = rand.nextInt(3);
            if (r == 0) t = new EnemyTarget();
            else if (r == 1) t = new FriendlyTarget();
            else t = new DevilTarget();
        }

        double x = rand.nextInt(700);
        double y = rand.nextInt(500);

        t.spawn(x, y);
        targets.add(t);
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
