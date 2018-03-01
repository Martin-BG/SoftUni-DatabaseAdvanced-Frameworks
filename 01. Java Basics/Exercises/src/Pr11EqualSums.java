import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Pr11EqualSums {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays
                .stream(reader.readLine().trim().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int length = input.length;

        long[] sum = new long[length];

        long maxSum = 0;

        for (int i = 0; i < length; i++) {
            maxSum += input[i];
            sum[i] = maxSum;
        }

        long leftSum;
        long rightSum;

        for (int i = 0; i < length; i++) {
            leftSum = sum[i] - input[i];
            rightSum = maxSum - sum[i];

            if (leftSum == rightSum) {
                System.out.println(i);
                return;
            }
        }

        System.out.println("no");
    }
}
