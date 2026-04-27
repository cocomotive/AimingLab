package Managers;//import Targets.Targets.DevilTarget;

import Aiming.GameState;
import Targets.Target;
import Targets.DevilTarget;
import Targets.EnemyTarget;
import Targets.FriendlyTarget;

import java.util.*;
import java.awt.Point;

public class TargetManager {

    private List<Target> targets = new ArrayList<>();
    private int maxTargets = 20;
    private Random rand = new Random();

    public void spawnRandom(GameState state) {

        if (targets.size() < maxTargets) {
            Target t;

            if (state == GameState.PRACTICE) {
                t = new FriendlyTarget();
            } else {
                int r = rand.nextInt(3);
                if (r == 0) t = new EnemyTarget();
                else if (r == 1) t = new FriendlyTarget();
                else t = new DevilTarget();
            }

            // Obtener posiciones disponibles
            List<Point> availableSpots = getAvailableSpawnPoints();

            // Si hay posiciones disponibles, spawnear en una al azar
            if (!availableSpots.isEmpty()) {
                Point p = availableSpots.get(rand.nextInt(availableSpots.size()));
                t.spawn(p.x, p.y);
                targets.add(t);
            }
        }
    }

    private List<Point> getAvailableSpawnPoints() {
        List<Point> available = new ArrayList<>();
        int spacing = 120; // Separación mínima entre targets
        int padding = 50;  // Margen desde los bordes

        for (int x = padding; x < 700; x += spacing) {
            for (int y = padding; y < 500; y += spacing) {
                if (!isOverlapping(x, y, spacing)) {
                    available.add(new Point(x, y));
                }
            }
        }
        return available;
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