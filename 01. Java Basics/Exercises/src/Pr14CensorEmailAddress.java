import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pr14CensorEmailAddress {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String email = reader.readLine();
        String text = reader.readLine();

        String censored = new String(new char[email.lastIndexOf("@")]).replaceAll("\0", "*")
                + email.substring(email.lastIndexOf("@"));

        System.out.println(text.replaceAll(email, censored));
    }
}
