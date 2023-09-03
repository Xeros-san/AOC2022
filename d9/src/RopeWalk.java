import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;

public class RopeWalk {

    private record Position(int x, int y) {}

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d6/input/input.txt"));
        List<Position> positions = new ArrayList<>();
        Position currentT = new Position(0, 0);
        Position currentH = new Position(0, 0);

        String[] line;
        while (sc.hasNextLine()) {
            line = sc.nextLine().split(" ");

            for (int i = 0; i < Integer.parseInt(line[1]); i++) {
                if (line[0].equals("U")) currentH.y += 1;
            }

        }

    }

}
