package pr07_mankind;

import pr07_mankind.model.Student;
import pr07_mankind.model.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] tokens = reader.readLine().split("\\s+");
            System.out.println((new Student(tokens[0], tokens[1], tokens[2])));

            System.out.println();

            tokens = reader.readLine().split("\\s+");
            System.out.println((new Worker(tokens[0], tokens[1],
                    Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]))));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}
