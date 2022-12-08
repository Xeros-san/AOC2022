import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class TreeCount {

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d8/input/input.txt"));
        int[][] forest = null;
        int pos = 0;

        while (sc.hasNextLine()) {
            char[] line = sc.nextLine().toCharArray();
            if (forest == null) {
                System.out.println(Arrays.toString(line));
                forest = new int[line.length][line.length];
            }
            for (int i = 0; i < line.length; i++) {
                forest[pos][i] = Character.getNumericValue(line[i]);
            }

            pos++;
        }

        Arrays.stream(forest).forEach(l -> System.out.println(Arrays.stream(l).mapToObj(String::valueOf).reduce((i1, i2) -> i1 + " " +i2)));

        int[][] cop = Arrays.stream(forest).map(l -> Arrays.copyOf(l, l.length)).toArray(int[][]::new); // making a deep copy

        // part 1
        int max_n, max_s, max_e, max_w;
        for (int i = 1; i < forest.length-1; i++) {
            for (int j = 1; j < forest.length-1; j++) {
                max_n = 0;
                max_s = 0;
                max_e = 0;
                max_w = 0;
                for (int n = 1; (i - n) >= 0; n++) {
                    if (max_n < forest[(i-n)][j]) max_n = forest[(i-n)][j];
                }
                for (int s = 1; (i + s) < forest.length; s++) {
                    if (max_s < forest[(i+s)][j]) max_s = forest[(i+s)][j];
                }
                for (int w = 1; (j - w) >= 0; w++) {
                    if (max_w < forest[i][(j-w)]) max_w = forest[i][(j-w)];
                }
                for (int e = 1; (j + e) < forest.length; e++) {
                    if (max_e < forest[i][(j+e)]) max_e = forest[i][(j+e)];
                }

                if (forest[i][j] <= max_n && forest[i][j] <= max_s && forest[i][j] <= max_w && forest[i][j] <= max_e) {
                    cop[i][j] = -1;
                }
            }
        }

        System.out.println(Arrays.stream(forest).mapToInt(l -> (int) Arrays.stream(l).filter(i -> i >= 0).count()).sum());
        System.out.println(Arrays.stream(cop).mapToInt(l -> (int) Arrays.stream(l).filter(i -> i >= 0).count()).sum());

        // part 2
        int scs = 0;
        int tempSCS;
        for (int i = 1; i < forest.length-1; i++) {
            for (int j = 1; j < forest.length-1; j++) {
                int s_n = i;
                int s_s = (forest.length - (i + 1));
                int s_w = j;
                int s_e = (forest.length - (j + 1));

                for (int n = 1; (i - n) >= 0; n++) {
                    if (forest[i][j] <= forest[(i - n)][j]) {
                        s_n = n;
                        break;
                    }
                }
                for (int s = 1; (i + s) < forest.length; s++) {
                    if (forest[i][j] <= forest[(i + s)][j]) {
                        s_s = s;
                        break;
                    }
                }
                for (int w = 1; (j - w) >= 0; w++) {
                    if (forest[i][j] <= forest[i][(j - w)]) {
                        s_w = w;
                        break;
                    }
                }
                for (int e = 1; (j + e) < forest.length; e++) {
                    if (forest[i][j] <= forest[i][(j + e)]) {
                        s_e = e;
                        break;
                    }
                }

                tempSCS = s_n * s_s * s_w * s_e;

                if (scs < tempSCS) scs = tempSCS;
            }
        }
        System.out.println(scs);
    }
}
