package Pr02AdvertisementMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class AdvertisementMessage {

    private static final String[] PHRASES = new String[]{
            "Excellent product.",
            "Such a great product.",
            "I always use that product.",
            "Best product of its category.",
            "Exceptional product.",
            "I can’t live without this product."};

    private static final String[] EVENTS = new String[]{
            "Now I feel good.",
            "I have succeeded with this product.",
            "Makes miracles. I am happy of the results!",
            "I cannot believe but now I feel awesome.",
            "Try it yourself, I am very satisfied.",
            "I feel great!"};

    private static final String[] AUTHOR = new String[]{
            "Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};

    private static final String[] CITIES = new String[]{
            "Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int messagesCount = Integer.parseInt(reader.readLine());

        StringBuilder messages = new StringBuilder();
        Random random = new Random();

        while (messagesCount-- > 0) {
            messages.append(PHRASES[random.nextInt(PHRASES.length)]).append(" ")
                    .append(EVENTS[random.nextInt(EVENTS.length)]).append(" ")
                    .append(AUTHOR[random.nextInt(AUTHOR.length)]).append(" - ")
                    .append(CITIES[random.nextInt(CITIES.length)])
                    .append(System.lineSeparator());
        }

        System.out.println(messages.toString().trim());
    }
}
