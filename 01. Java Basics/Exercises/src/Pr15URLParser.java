import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Pr15URLParser {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String url = reader.readLine();

        System.out.println(DecomposeURLWithSubstring(url));
//        System.out.println(DecomposeURLWithSplit(url));
    }

    private static String DecomposeURLWithSubstring(String url) {

        String protocol = "";
        String resource = "";
        String server;

        if (url.indexOf("://") > 0) {
            protocol = url.substring(0, url.indexOf("://"));
            url = url.substring(protocol.length() + 3);
        }

        if (url.indexOf('/') != -1) {
            server = url.substring(0, url.indexOf('/'));

            if (server.length() != url.length()) {
                resource = url.substring(server.length() + 1);
            }
        } else {
            server = url;
        }

        return String.format("[protocol] = \"%s\"%n[server] = \"%s\"%n[resource] = \"%s\"%n",
                protocol, server, resource);
    }

    private static String DecomposeURLWithSplit(String url) {
        String protocol = "";
        String server;
        String resource = "";

        String[] split = url.split("://");
        if (split.length == 2) {
            protocol = split[0];
            split[0] = split[1];
        }

        split = split[0].split("/");
        server = split[0];

        if (split.length > 1) {
            resource = Arrays
                    .stream(Arrays.copyOfRange(split, 1, split.length))
                    .collect(Collectors.joining("/"));
        }

        return String.format("[protocol] = \"%s\"%n[server] = \"%s\"%n[resource] = \"%s\"%n",
                protocol, server, resource);
    }
}
