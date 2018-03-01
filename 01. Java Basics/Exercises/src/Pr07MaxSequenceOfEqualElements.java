import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr07MaxSequenceOfEqualElements {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = input.length;

        if (length == 0) {
            return;
        }

        int currentNumber = input[0];
        int currentLength = 1;
        int currentStart = 0;
        int bestStart = 0;
        int bestLength = 1;

        for (int i = 1; i < length; i++) {

            if (input[i] == currentNumber) {

                currentLength++;

            } else {

                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    bestStart = currentStart;
                }

                currentLength = 1;
                currentNumber = input[i];
                currentStart = i;
            }
        }

        if (currentLength > bestLength) {
            bestLength = currentLength;
            bestStart = currentStart;
        }

        System.out.println(
                Arrays.toString(
                        Arrays.copyOfRange(input, bestStart, bestStart + bestLength))
                        .replaceAll("[\\[\\],]", ""));
    }
}
