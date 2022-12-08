import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CalCount {

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d1/input/input.txt"));
        int[] elves = new int[2];
        int sum = 0;
        int pos = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) {
                if (elves.length == pos) elves = Arrays.copyOf(elves, elves.length * 2);
                elves[pos] = sum;
                sum = 0;
                pos++;
            } else {
                sum += Integer.parseInt(line);
            }
        }

        Arrays.stream(elves).sorted().forEach(System.out::println);
    }

}
