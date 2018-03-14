package pr07_mankind.model;

public class Student extends Human {

    private static final int MIN_FACULTY_NUMBER_LENGTH = 5;
    private static final int MAX_FACULTY_NUMBER_LENGTH = 10;

    private String facultyNumber;

    public Student(String firstName, String lastName, String facultyNumber) {
        super(firstName, lastName);
        this.setFacultyNumber(facultyNumber);
    }

    public String getFacultyNumber() {
        return this.facultyNumber;
    }

    private void setFacultyNumber(String facultyNumber) {
        if (!facultyNumber.matches("\\d+") ||
                facultyNumber.length() <= MIN_FACULTY_NUMBER_LENGTH ||
                facultyNumber.length() >= MAX_FACULTY_NUMBER_LENGTH) {
            throw new IllegalArgumentException("Invalid faculty number!");
        }

        this.facultyNumber = facultyNumber;
    }

    @Override
    public String toString() {
        return String.format("%s%nFaculty number: %s", super.toString(), this.getFacultyNumber());
    }
}
