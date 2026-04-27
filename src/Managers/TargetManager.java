package Managers;

import Aiming.GameState;
import Targets.Target;
import Targets.DevilTarget;
import Targets.EnemyTarget;
import Targets.FriendlyTarget;

import java.util.*;

public class TargetManager {

    private List<Target> targets = new ArrayList<>();
    private int maxTargets = 15;
    private Random rand = new Random();

    // Dimensiones de pantalla full
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int TARGET_SIZE = 90;
    private static final int COLLISION_PADDING = 10;

    private GameState currentState = GameState.MENU;

    public void spawnRandom(GameState state) {

        if (currentState != GameState.PRACTICE &&
                currentState != GameState.SURVIVAL) {
            return;
        }

        if (state != GameState.PRACTICE && state != GameState.SURVIVAL) {
            return;
        }

        Iterator<Target> it = targets.iterator();
        while (it.hasNext()) {
            Target t = it.next();
            if (t.isExpired()) {
                t.destroy();
                it.remove();
            }
        }

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
        int maxAttempts = 1000;


        do {
            x = COLLISION_PADDING + rand.nextDouble() * (SCREEN_WIDTH - TARGET_SIZE - 2 * COLLISION_PADDING);
            y = COLLISION_PADDING + rand.nextDouble() * (SCREEN_HEIGHT - TARGET_SIZE - 2 * COLLISION_PADDING);
            attempts++;
        } while (isOverlappingWithSprite(x, y) && attempts < maxAttempts);

        // Solo spawnear si se encontró posición válida
        if (attempts < maxAttempts) {
            t.spawn(x, y);
            targets.add(t);
        }
    }


    private boolean isOverlappingWithSprite(double centerX, double centerY) {
        // Calcular bounds del nuevo sprite (considerando que será centrado)
        double newLeft = centerX - TARGET_SIZE / 2.0;
        double newRight = centerX + TARGET_SIZE / 2.0;
        double newTop = centerY - TARGET_SIZE / 2.0;
        double newBottom = centerY + TARGET_SIZE / 2.0;

        // Comparar con todos los sprites existentes
        for (Target t : targets) {
            double existingLeft = t.getX();
            double existingRight = t.getX() + t.getWidth();
            double existingTop = t.getY();
            double existingBottom = t.getY() + t.getHeight();

            // AABB collision con padding
            if (!(newRight + COLLISION_PADDING < existingLeft ||
                    newLeft - COLLISION_PADDING > existingRight ||
                    newBottom + COLLISION_PADDING < existingTop ||
                    newTop - COLLISION_PADDING > existingBottom)) {
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

    public void setState(GameState state) {
        this.currentState = state;
    }

    public int getTargetCount() {
        return targets.size();
    }

}