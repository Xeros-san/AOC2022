import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.UnaryOperator;

public class CrateMover {

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d5/input/input.txt"));
        List<List<String>> crates = new ArrayList<>();
        Queue<UnaryOperator<List<List<String>>>> operators = new LinkedList<>();
        List<String> stack = new ArrayList<>();

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            stack.add(line);
        }

        int length = Arrays.stream(stack.get(stack.size() - 1).split(" ")).filter(s -> !s.isEmpty()).mapToInt(Integer::parseInt).max().getAsInt();

        // filling crates
        for (int i = 1; i <= length; i++) {
            List<String> temp = new ArrayList<>();
            int pos = stack.get(stack.size() - 1).indexOf(String.valueOf(i));
            for (int j = stack.size()-2; j >= 0; j--) {
                if (stack.get(j).length() > pos && stack.get(j).charAt(pos) != ' ') {
                    temp.add(String.valueOf(stack.get(j).charAt(pos)));
                }
            }
            crates.add(temp);
        }

        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(" ");

            int move = Integer.parseInt(line[1]);
            int from = Integer.parseInt(line[3]) - 1;
            int to = Integer.parseInt(line[5]) - 1;

            operators.add(list -> {
                List<String> temp = list.get(from).subList(list.get(from).size() - move, list.get(from).size());
                // Collections.reverse(temp); // Uncomment for part 1
                list.get(to).addAll(temp);
                for (int i = 0; i < move; i++) {
                    list.get(from).remove(list.get(from).size() - 1);
                }
                return list;
            });
        }

        for (UnaryOperator<List<List<String>>> operation: operators) {
            crates = operation.apply(crates);
        }

        crates.forEach(l -> System.out.println(l.stream().reduce(String::concat)));
    }
}
