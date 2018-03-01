import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr09MostFrequentNumber {

    private static final int MAX_VALUE = 65535;
    private static final int COUNTER = 0;
    private static final int FIRST_INDEX = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] occurrences = new int[MAX_VALUE][2];

        for (int i = 0; i < input.length; i++) {
            int num = input[i];

            if (occurrences[num][COUNTER] == 0) {
                occurrences[num][FIRST_INDEX] = i;
            }

            occurrences[num][COUNTER]++;
        }

        int maxOccurrences = 0;
        int index = MAX_VALUE;
        int number = -1;

        for (int i = 0; i < MAX_VALUE; i++) {
            if (occurrences[i][COUNTER] > maxOccurrences ||
                    (occurrences[i][COUNTER] == maxOccurrences && occurrences[i][FIRST_INDEX] < index)) {

                number = i;
                maxOccurrences = occurrences[number][COUNTER];
                index = occurrences[number][FIRST_INDEX];

            }
        }

        if (number > -1) {
            System.out.println(number);
        }
    }
}
