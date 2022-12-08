import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class intervalCont {

    private record Interval(int start, int end) {
        public boolean contains(Interval i2) {
            return this.start <= i2.start && this.end >= i2.end;
        }

        public boolean fullyContains(Interval i2) {
            return this.contains(i2) || i2.contains(this);
        }

        public boolean anyOverlap(Interval i2) {
            if (this.start < i2.start) {
               return this.end >= i2.start;
            } else if (this.start > i2.start) {
                return i2.end >= this.start;
            }
            return true;
        }
    }

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d4/input/input.txt"));
        List<Interval[]> intervals = new ArrayList<>();

        for (int pos = 0; sc.hasNextLine(); pos++) {
            String line = sc.nextLine();

            intervals.add(new Interval[2]);
            int i = 0;
            for (String inter: line.split(",")) {
                intervals.get(pos)[i] = new Interval(
                    Integer.parseInt(inter.split("-")[0]),
                    Integer.parseInt(inter.split("-")[1]));
                i++;
            }
        }

        // part 1
        System.out.println(intervals.stream().filter(arr -> arr[0].fullyContains(arr[1])).count());
        // part 2
        System.out.println(intervals.stream().filter(arr -> arr[0].anyOverlap(arr[1])).count());
    }

}
