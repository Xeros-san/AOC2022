import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RPSPredictor {
    private static Scanner sc;



    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d2/input/input.txt"));
        List<String[]> list = new ArrayList<>();
        int score = 0;

        while (sc.hasNextLine()) {
            list.add(sc.nextLine().split(" "));
        }

        score = list.stream().mapToInt(line -> {
            if (Objects.equals(line[0], "A")) { // Rock
                if (Objects.equals(line[1], "X")) return 3; // 3
                if (Objects.equals(line[1], "Y")) return 4; // 1 + 3
                if (Objects.equals(line[1], "Z")) return 8; // 2 + 6
            } else if (Objects.equals(line[0], "B")) { // Paper
                if (Objects.equals(line[1], "X")) return 1; // 1
                if (Objects.equals(line[1], "Y")) return 5; // 2 + 3
                if (Objects.equals(line[1], "Z")) return 9; // 3 + 6
            } else { // Scissor
                if (Objects.equals(line[1], "X")) return 2; // 2
                if (Objects.equals(line[1], "Y")) return 6; // 3 + 3
                if (Objects.equals(line[1], "Z")) return 7; // 1 + 6
            }
            return 0;
        }).sum();

        System.out.println(score);
    }

}
