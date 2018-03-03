package Pr08BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Library library = new Library("The Library", new ArrayList<>());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            int lines = Integer.parseInt(reader.readLine());

            while (lines-- > 0) {
                String[] tokens = reader.readLine().split("\\s+");
                library.addBook(
                        new Book(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], Double.parseDouble(tokens[5])));
            }

            System.out.print(library.getPriceByAuthor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
