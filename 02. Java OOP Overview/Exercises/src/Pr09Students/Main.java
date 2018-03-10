package Pr09Students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            String name;

            while (!"END".equalsIgnoreCase(name = reader.readLine())) {
                new Student(name);
            }

            System.out.print(Student.studentsCreated());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
