package pr05_border_control;

import pr05_border_control.contracts.Identable;
import pr05_border_control.model.Citizen;
import pr05_border_control.model.Robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Identable> identables = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;

            while (!"End".equalsIgnoreCase(line = reader.readLine().trim())) {
                String[] tokens = line.split("\\s+");

                if (tokens.length == 2) {
                    String model = tokens[0];
                    String id = tokens[1];
                    identables.add(new Robot(id, model));
                } else if (tokens.length == 3) {
                    String name = tokens[0];
                    int age = Integer.parseInt(tokens[1]);
                    String id = tokens[2];
                    identables.add(new Citizen(id, name, age));
                }
            }

            String fakeIDs = reader.readLine().trim();

            identables.stream()
                    .filter(identable -> identable.getId().endsWith(fakeIDs))
                    .forEach(identable -> System.out.println(identable.getId()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
