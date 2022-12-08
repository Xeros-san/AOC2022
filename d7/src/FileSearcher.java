import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FileSearcher {

    private static Scanner sc;

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d7/input/input.txt"));
        String line = sc.nextLine();

        Directory root = new Directory("/", new ArrayList<>(), new ArrayList<>(), null);

        Directory currentDir = root;

        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.startsWith("$")) {
                if (line.startsWith("$ cd")) {
                    if (line.equals("$ cd ..")) {
                        currentDir = currentDir.parent;
                    } else {
                        String dirName = line.split(" ")[2];
                        currentDir = currentDir.directories.stream().filter(d -> d.name.equals(dirName)).findFirst().get();
                    }
                }
            } else {
                if (line.startsWith("dir")) {
                    currentDir.directories.add(new Directory(line.split(" ")[1], new ArrayList<>(), new ArrayList<>(), currentDir ));
                } else {
                    String[] fileInput = line.split(" ");
                    currentDir.files.add(new custFile(fileInput[1], Integer.parseInt(fileInput[0])));
                }
            }
        }

        System.out.println(root.print());

        System.out.println(root.findAll().stream().mapToInt(Directory::getSize).sum());

        // part 2
        int unusedSpace = 70_000_000 - root.getSize();
        System.out.println(unusedSpace);
        System.out.println(root.findSmallest(30_000_000 - unusedSpace));
    }

    private record custFile(String name, int size){ }

    private record Directory(String name, List<custFile> files, List<Directory> directories, Directory parent) {
        public int getSize() {
            return directories.stream().mapToInt(Directory::getSize).sum() + files.stream().mapToInt(custFile::size).sum();
        }

        public String print() {
            return this.print("");
        }

        public String print(String beforeEachLine) {
            StringBuilder sb = new StringBuilder();
            sb.append("dir ").append(this.name).append(" size: ").append(this.getSize()).append(System.lineSeparator());

            files.forEach(f -> sb.append(beforeEachLine).append("\t").append("file: ").append(f.name).append("size: ").append(f.size).append(System.lineSeparator()));
            directories.forEach(d -> sb.append(beforeEachLine).append("\t").append(d.print()));

            return sb.toString();
        }

        private List<Directory> toList() {
            List<Directory> list = new ArrayList<>();

            list.add(this);
            directories.forEach(d -> list.addAll(d.toList()));
            return list;
        }

        public List<Directory> findAll() {
            return this.toList().stream().filter(d -> d.getSize() <= 100_000).toList();
        }

        public int findSmallest(int size) {
            return this.toList().stream().mapToInt(Directory::getSize).filter(i -> i >= size).min().getAsInt();
        }
    }

}
