package Managers;

import java.io.*;

public class ScoreManager {

    private static final String FILE = "score.dat";

    public static void save(int score) {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            int score = Integer.parseInt(br.readLine());
            return score;
        } catch (Exception e) {
            return 0;
        }
    }
}