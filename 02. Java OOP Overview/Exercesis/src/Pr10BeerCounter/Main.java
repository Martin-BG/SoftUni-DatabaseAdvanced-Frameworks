package Pr10BeerCounter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String input;

            while (!"END".equalsIgnoreCase(input = reader.readLine())) {
                int[] beers = Arrays
                        .stream(input.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                BeerCounter.BuyBeer(beers[0]);
                BeerCounter.DrinkBeer(beers[1]);
            }
        } catch (Exception e) {
            // Ignore invalid input from Judge
        } finally {
            System.out.printf("%d %d%n",
                    BeerCounter.getBeerInStock(),
                    BeerCounter.getBeersDrankCount());
        }
    }
}
