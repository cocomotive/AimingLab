package Managers;

import Aiming.*;

import java.util.*;

public class GameManager {

    private GameState state = GameState.MENU;

    private BackgroundManager bg = new BackgroundManager();
    private TargetManager targetManager = new TargetManager();
    private UIRenderer uiRenderer; // NUEVO
    private List<Button> buttons = new ArrayList<>();

    private int score = 0;
    private int time = 60;
    private int bestScore = ScoreManager.load();

    public GameManager() {
        uiRenderer = new UIRenderer(); // NUEVO
        showMenu();
    }

    // ===== MENU =====
    public void showMenu() {
        clearUI();
        state = GameState.MENU;
        bg.setBackground("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Background.jpg");

        buttons.add(new Button(300,200,"/Users/andreaparra/workspace/Aiming/Aiming/Resources/PracticeButton.png", this::startPractice));
        buttons.add(new Button(300,300,"/Users/andreaparra/workspace/Aiming/Aiming/Resources/SurvivalButton.png", this::startSurvival));
        buttons.add(new Button(300,400,"/Users/andreaparra/workspace/Aiming/Aiming/Resources/ExitButton.png", () -> System.exit(0)));
    }

    // ===== PRACTICE =====
    public void startPractice() {
        clearUI();
        state = GameState.PRACTICE;
        bg.setBackground("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Background.jpg");

        new Thread(() -> {
            while (state == GameState.PRACTICE) {
                targetManager.spawnRandom(state);
                sleep(800);
            }
        }).start();
    }

    // ===== SURVIVAL =====
    public void startSurvival() {
        clearUI();
        state = GameState.SURVIVAL;

        score = 0;
        time = 60;

        bg.setBackground("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Background.jpg");

        uiRenderer.show(); // NUEVO: Mostrar el UI
        uiRenderer.update(score, time, bestScore); // NUEVO

        new Thread(this::survivalLoop).start();
    }

    private void survivalLoop() {
        long start = System.currentTimeMillis();

        while (state == GameState.SURVIVAL) {
            long elapsed = (System.currentTimeMillis() - start) / 1000;
            int spawnRate = Math.max(200, 1000 - (int)elapsed * 15);

            targetManager.spawnRandom(state);

            time = 60 - (int)elapsed;

            // NUEVO: Actualizar UI cada frame
            uiRenderer.update(score, time, bestScore);

            if (time <= 0) {
                showGameOver();
                break;
            }

            sleep(spawnRate);
        }
    }

    // ===== GAME OVER =====
    public void showGameOver() {
        clearUI();
        state = GameState.GAME_OVER;

        if (score > bestScore) {
            bestScore = score;
            ScoreManager.save(score);
        }

        bg.setBackground("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Background.jpg");

        buttons.add(new Button(300,300,"images/retry_btn.png", this::startSurvival));
        buttons.add(new Button(300,400,"images/menu_btn.png", this::showMenu));
    }

    // ===== INPUT =====
    public void handleClick(double x, double y) {
        for (Button b : buttons) {
            if (b.isClicked(x, y)) {
                b.click();
                return;
            }
        }

        if (state == GameState.PRACTICE || state == GameState.SURVIVAL) {
            targetManager.handleClick(x, y, this);
        }
    }

    // ===== UTILS =====
    public void addScore(int v) {
        score += v;
    }

    public void addTime(int v) { time += v; }

    private void clearUI() {
        targetManager.clear();
        uiRenderer.clear(); // NUEVO

        for (Button b : buttons) {
            b.destroy();
        }
        buttons.clear();
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }
}