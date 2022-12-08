import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SackCalc {
    private enum Alphabet {
        a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;

        public static int getNum(String targ) {
            return valueOf(targ).ordinal()+1;
        }

        public static int getNum(char targ) {
            return valueOf(String.valueOf(targ)).ordinal()+1;
        }
    }

    private static Scanner sc;
    private static List<String[]> items1 = new ArrayList<>();
    private static List<String[]> items2 = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        sc = new Scanner(new File("d3/input/input.txt"));

        // position for part1
        int pos = 0;
        // positions for part2
        int pos1 = -1;
        int pos2 = 0;

        while (sc.hasNextLine()) {
            String sack = sc.nextLine();

            // fill items 1
            items1.add(new String[2]);
            items1.get(pos)[0] = sack.substring(0, sack.length()/2);
            items1.get(pos)[1] = sack.substring(sack.length()/2);

            pos++;

            // fill items 2
            if (pos2 == 0) {
                pos1++;
                items2.add(new String[3]);
            }

            items2.get(pos1)[pos2] = sack;
            pos2 = (pos2 + 1) % 3;
        }

        part1();
        part2();
    }

    private static void part1() {
        int sum = 0;

        for (String[] s : items1) {
            for (char c1 : s[0].toCharArray()) {
                if (s[1].contains(""+c1)) {
                    sum += (Alphabet.getNum(c1));
                }
            }
        }

        System.out.println(sum);
    }

    private static void part2() {
        int sum = 0;
        for (String[] sacks : items2) {
            for (char c : sacks[0].toCharArray()) {
                if (sacks[1].contains(""+c) && sacks[2].contains(""+c)) {
                    sum += (Alphabet.getNum(c));
                    break;
                }
            }
        }

        System.out.println(sum);
    }

}
