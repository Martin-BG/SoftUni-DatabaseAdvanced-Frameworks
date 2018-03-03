package Pr09Students;

public class Student {

    private static int counter = 0;

    private final String name;

    public Student(String name) {
        this.name = name;
        counter++;
    }

    public static int studentsCreated() {
        return counter;
    }
}
