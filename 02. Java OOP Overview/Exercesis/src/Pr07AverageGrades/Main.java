package Pr07AverageGrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final double MIN_GRADE = 5.0;
    private static final String INPUT_SEPARATOR = "\\s+";

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int lines = Integer.parseInt(reader.readLine());

            while (lines-- > 0) {
                String[] tokens = reader.readLine().trim().split(INPUT_SEPARATOR);

                String name = tokens[0];
                double[] grades = Arrays
                        .stream(Arrays.copyOfRange(tokens, 1, tokens.length))
                        .mapToDouble(Double::parseDouble)
                        .toArray();

                students.add(new Student(name, grades));
            }

            StringBuilder sb = new StringBuilder();

            students.stream()
                    .filter(student -> student.getAverageGrade() >= MIN_GRADE)
                    .sorted()
                    .forEach(student -> sb.append(student.toString()).append(System.lineSeparator()));

            System.out.println(sb.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
