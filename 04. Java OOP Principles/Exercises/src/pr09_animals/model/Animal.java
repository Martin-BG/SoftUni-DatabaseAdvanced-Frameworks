package pr09_animals.model;

import pr09_animals.constants.MessageConstants;

public class Animal {

    private static final String SOUND = "Not implemented!";

    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(MessageConstants.INVALID_INPUT);
        }
        this.name = name.trim();
    }

    private void setAge(int age) {
        if (age < 1) {
            throw new IllegalArgumentException(MessageConstants.INVALID_INPUT);
        }
        this.age = age;
    }

    protected void setGender(String gender) {
        if (gender == null || gender.trim().isEmpty() ||
                (!MessageConstants.MALE.equalsIgnoreCase(gender.trim()) &&
                        !MessageConstants.FEMALE.equalsIgnoreCase(gender.trim()))) {
            throw new IllegalArgumentException(MessageConstants.INVALID_INPUT);
        }
        this.gender = gender.trim();
    }

    public String getInfo() {
        return String.format("%s%n%s %d %s%n%s", this.getClass().getSimpleName(), this.name, this.age, this.gender, this.produceSound());
    }

    public String produceSound() {
        return SOUND;
    }
}
