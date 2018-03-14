package pr09_animals.model;

import pr09_animals.constants.Constants;

public abstract class Animal {

    private String name;
    private int age;
    private String gender;
    private final String type;

    Animal(String name, int age, String gender, final String type) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.type = type;
    }

    private void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(Constants.INVALID_INPUT);
        }

        this.name = name;
    }

    private void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException(Constants.INVALID_INPUT);
        }

        this.age = age;
    }

    private void setGender(String gender) {
        if (gender == null || gender.isEmpty() ||
                (!Constants.MALE.equalsIgnoreCase(gender) &&
                        !Constants.FEMALE.equalsIgnoreCase(gender))) {
            throw new IllegalArgumentException(Constants.INVALID_INPUT);
        }

        this.gender = gender;
    }

    public String getInfo() {
        return String.format(Constants.ANIMAL_INFO_STRING_FORMAT,
                this.type, this.name, this.age, this.gender, this.produceSound());
    }

    String produceSound() {
        return Constants.ANIMAL_SOUND;
    }
}
