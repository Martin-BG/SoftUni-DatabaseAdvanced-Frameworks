package pr09_animals;

import pr09_animals.constants.MessageConstants;
import pr09_animals.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Animal> animals = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            while (!"beast!".equalsIgnoreCase(input = reader.readLine())) {
                String type = input.trim().toLowerCase();
                String[] tokens = reader.readLine().trim().split("\\s+");

                Animal animal;
                try {
                    if (tokens.length != 3 || tokens[0].isEmpty() || tokens[1].isEmpty() || tokens[2].isEmpty()) {
                        throw new IllegalArgumentException(MessageConstants.INVALID_INPUT);
                    }

                    String name = tokens[0];
                    int age = Integer.parseInt(tokens[1]);
                    String gender = tokens[2];

                    switch (type) {
                    case "animal":
                        animal = new Animal(name, age, gender);
                        break;
                    case "cat":
                        animal = new Cat(name, age, gender);
                        break;
                    case "kitten":
                        animal = new Kitten(name, age, gender);
                        break;
                    case "tomcat":
                        animal = new Tomcat(name, age, gender);
                        break;
                    case "dog":
                        animal = new Dog(name, age, gender);
                        break;
                    case "frog":
                        animal = new Frog(name, age, gender);
                        break;
                    default:
                        throw new IllegalArgumentException(MessageConstants.INVALID_INPUT);
                    }

                    animals.add(animal);
                } catch (IllegalArgumentException iae) {
                    System.out.println(iae.getMessage());
                }
            }

            for (Animal animal : animals) {
                System.out.println(animal.getInfo());
                animal.produceSound();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
