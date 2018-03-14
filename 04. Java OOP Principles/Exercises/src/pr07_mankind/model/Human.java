package pr07_mankind.model;

import pr07_mankind.contracts.Nameable;

public abstract class Human implements Nameable {

    private static final int MIN_FIRST_NAME_LENGTH = 4;
    private static final int MIN_LAST_NAME_LENGTH = 3;

    private String firstName;
    private String lastName;

    Human(String firstName, String lastName) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    private void setFirstName(String firstName) {
        if (Character.isLowerCase(firstName.charAt(0))) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: firstName");
        }

        if (firstName.length() < MIN_FIRST_NAME_LENGTH) {
            throw new IllegalArgumentException("Expected length at least 4 symbols!Argument: firstName");
        }

        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    void setLastName(String lastName) {
        if (Character.isLowerCase(lastName.charAt(0))) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        }

        if (lastName.length() < MIN_LAST_NAME_LENGTH) {
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        }

        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("First Name: %s%nLast Name: %s", this.getFirstName(), this.getLastName());
    }
}
