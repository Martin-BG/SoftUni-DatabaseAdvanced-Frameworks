import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr13FitStringIn20Chars {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        String result;
        if (input.length() < 20) {
            result = String.format("%s%s",
                    input,
                    new String(new char[20 - input.length()]).replaceAll("\0", "*"));
        } else {
            result = input.substring(0, 20);
        }

        System.out.println(result);
    }
}
