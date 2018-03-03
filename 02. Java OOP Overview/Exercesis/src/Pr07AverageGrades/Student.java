package Pr07AverageGrades;

import java.util.Arrays;

public class Student implements Comparable<Student> {

    final private String name;
    final private double[] grades;
    final private double averageGrade;

    public Student(String name, double[] grades) {
        this.name = name;
        this.grades = grades;
        this.averageGrade = Arrays.stream(this.grades).average().orElse(0d);
    }

    public double getAverageGrade() {
        return this.averageGrade;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int compareTo(Student other) {
        int res = this.getName().compareTo(other.getName());
        if (res == 0) {
            res = Double.compare(other.getAverageGrade(), this.getAverageGrade());
        }
        return res;
    }

    @Override
    public String toString() {
        return String.format("%s -> %.2f",
                this.getName(),
                this.getAverageGrade());
    }
}
