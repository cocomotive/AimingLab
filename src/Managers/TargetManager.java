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

            // Intentar encontrar una posición válida
            Point p = findValidSpawnPoint();

            if (p != null) {
                t.spawn(p.x, p.y);
                targets.add(t);
            }
        }
    }

    private Point findValidSpawnPoint() {
        int maxAttempts = 100;
        int spacing = 120;
        int padding = 60;
        int gridSize = 20; // Variación aleatoria dentro de cada celda

        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            // Generar una posición aleatoria
            double x = padding + rand.nextInt(700 - padding);
            double y = padding + rand.nextInt(500 - padding);

            // Verificar si no solapea
            if (!isOverlapping(x, y, spacing)) {
                return new Point((int)x, (int)y);
            }
        }

        return null; // Si no encuentra posición después de muchos intentos
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