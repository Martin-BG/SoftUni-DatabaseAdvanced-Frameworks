import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr06CompareCharArrays {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] charArrOne = reader.readLine().replaceAll("\\s+", "").toCharArray();
        char[] charArrTwo = reader.readLine().replaceAll("\\s+", "").toCharArray();

        Boolean charArrOneFirst = null;
        int min = Math.min(charArrOne.length, charArrTwo.length);

        for (int i = 0; i < min; i++) {
            if (charArrTwo[i] < charArrOne[i]) {
                charArrOneFirst = false;
                break;
            } else if (charArrTwo[i] > charArrOne[i]) {
                charArrOneFirst = true;
                break;
            }
        }

        if (charArrOneFirst == null) {
            charArrOneFirst = charArrOne.length < charArrTwo.length;
        }

        if (charArrOneFirst) {
            System.out.println(String.valueOf(charArrOne));
            System.out.println(String.valueOf(charArrTwo));
        } else {
            System.out.println(String.valueOf(charArrTwo));
            System.out.println(String.valueOf(charArrOne));
        }
    }
}
