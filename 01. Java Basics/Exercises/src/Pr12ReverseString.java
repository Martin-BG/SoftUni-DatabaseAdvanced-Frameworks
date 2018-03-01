import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr12ReverseString {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(new StringBuilder(reader.readLine()).reverse());
    }
}
