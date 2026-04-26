import Managers.BackgroundManager;
import Managers.ScoreManager;

import java.util.*;

public class GameManager {

    private GameState state = GameState.MENU;

    private BackgroundManager bg = new BackgroundManager();
    private TargetManager targetManager = new TargetManager();
    private List<Button> buttons = new ArrayList<>();

    private int score = 0;
    private int time = 60;
    private int bestScore = ScoreManager.load();

    public GameManager() {
        showMenu();
    }

    // ===== MENU =====
    public void showMenu() {

        clearUI();
        state = GameState.MENU;

        bg.setBackground("/Users/andreaparra/workspace/Aiming/Aiming/Resources/Background.jpg");

        buttons.add(new Button(300,200,"images/practice_btn.png", this::startPractice));
        buttons.add(new Button(300,300,"images/survival_btn.png", this::startSurvival));
        buttons.add(new Button(300,400,"images/exit_btn.png", () -> System.exit(0)));
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

        new Thread(this::survivalLoop).start();
    }

    private void survivalLoop() {

        long start = System.currentTimeMillis();

        while (state == GameState.SURVIVAL) {

            long elapsed = (System.currentTimeMillis() - start) / 1000;

            int spawnRate = Math.max(200, 1000 - (int)elapsed * 15);

            targetManager.spawnRandom(state);

            time--;

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
    public void addScore(int v) { score += v; }
    public void addTime(int v) { time += v; }

    private void clearUI() {
        targetManager.clear();

        for (Button b : buttons) {
            b.destroy();
        }
        buttons.clear();
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (Exception ignored) {}
    }
}
