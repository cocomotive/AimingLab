import java.io.*;

public class ScoreManager {

    private static final String FILE = "score.dat";

    public static void save(int score) {
        try (FileWriter fw = new FileWriter(FILE)) {
            fw.write(String.valueOf(score));
        } catch (IOException ignored) {}
    }

    public static int load() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return Integer.parseInt(br.readLine());
        } catch (Exception e) {
            return 0;
        }
    }
}
