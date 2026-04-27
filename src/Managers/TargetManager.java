package Managers;

import Aiming.GameState;
import Targets.Target;
import Targets.DevilTarget;
import Targets.EnemyTarget;
import Targets.FriendlyTarget;

import java.util.*;

public class TargetManager {

    private List<Target> targets = new ArrayList<>();
    private int maxTargets = 15;  // ✅ Reducido a 15
    private Random rand = new Random();

    // Dimensiones de pantalla full
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int TARGET_SIZE = 90;
    private static final int COLLISION_PADDING = 10;

    public void spawnRandom(GameState state) {
        if (targets.size() < maxTargets) {
            spawnTarget(state);
        }
    }

    private void spawnTarget(GameState state) {
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
        int maxAttempts = 50;  // Más intentos para garantizar spawn

        // ✅ Generar posición sin overlapping
        do {
            x = rand.nextDouble() * (SCREEN_WIDTH - TARGET_SIZE);
            y = rand.nextDouble() * (SCREEN_HEIGHT - TARGET_SIZE);
            attempts++;
        } while (isOverlapping(x, y, TARGET_SIZE) && attempts < maxAttempts);

        // Solo spawnear si se encontró posición válida
        if (attempts < maxAttempts) {
            t.spawn(x, y);
            targets.add(t);
        }
    }

    public boolean isOverlapping(double x, double y, double size) {
        // ✅ Detección de colisión mejorada
        double margin = size + COLLISION_PADDING;

        for (Target t : targets) {
            double tx = t.getX();
            double ty = t.getY();
            double tw = t.getWidth();
            double th = t.getHeight();

            // Collision AABB (Axis-Aligned Bounding Box)
            if (!(x + size < tx - COLLISION_PADDING ||
                    x > tx + tw + COLLISION_PADDING ||
                    y + size < ty - COLLISION_PADDING ||
                    y > ty + th + COLLISION_PADDING)) {
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

                // ✅ Spawn nuevo target inmediatamente después
                if (game.getGameState() == Aiming.GameState.PRACTICE ||
                        game.getGameState() == Aiming.GameState.SURVIVAL) {
                    spawnTarget(game.getGameState());
                }
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

    public int getTargetCount() {
        return targets.size();
    }
}