import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pr16ChangeToUppercase {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        final String regex = "(?<replace><upcase>(?<text>.*?)</upcase>)";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            input = input.replaceFirst(matcher.group("replace"), matcher.group("text").toUpperCase());
        }

        System.out.println(input);
    }
}
