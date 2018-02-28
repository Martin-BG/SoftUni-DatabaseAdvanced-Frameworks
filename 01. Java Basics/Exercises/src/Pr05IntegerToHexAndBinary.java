import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr05IntegerToHexAndBinary {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        System.out.println(Integer.toString(num, 16).toUpperCase());
        System.out.println(Integer.toString(num, 2));
    }
}
