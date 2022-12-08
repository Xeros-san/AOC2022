import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SeqFinder {

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d6/input/input.txt"));
        char[] seq = sc.nextLine().toCharArray();

        int seqLength = 3; // replace with 13 for part 2

        for (int i = seqLength; i < seq.length; i++) {
            Set<Character> check = new HashSet<>();

            for (int j = 0; j <= seqLength; j++) {
                check.add(seq[i-j]);
            }

            if (check.size() == 4) {
                System.out.println(i);
                break;
            }
        }

    }
}
