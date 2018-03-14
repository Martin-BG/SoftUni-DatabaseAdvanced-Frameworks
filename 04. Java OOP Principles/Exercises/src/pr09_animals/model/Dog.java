package pr09_animals.model;

import pr09_animals.constants.Constants;

public class Dog extends Animal {

    public Dog(String name, int age, String gender, String type) {
        super(name, age, gender, type);
    }

    @Override
    public String produceSound() {
        return Constants.DOG_SOUND;
    }
}
