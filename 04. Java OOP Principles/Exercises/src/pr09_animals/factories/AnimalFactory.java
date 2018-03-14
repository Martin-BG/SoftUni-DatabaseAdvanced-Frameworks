package pr09_animals.factories;

import pr09_animals.constants.Constants;
import pr09_animals.model.*;

public final class AnimalFactory {

    private static final AnimalFactory ourInstance = new AnimalFactory();

    private static final int PARAMS_LENGTH = 3;
    private static final int NAME_INDEX = 0;
    private static final int AGE_INDEX = 1;
    private static final int GENDER_INDEX = 2;

    private AnimalFactory() {
    }

    public static AnimalFactory getInstance() {
        return ourInstance;
    }

    public Animal produceAnimal(String animal, String... params) {

        if (params.length != PARAMS_LENGTH ||
                params[NAME_INDEX] == null ||
                params[AGE_INDEX] == null || !params[AGE_INDEX].matches("\\d+") ||
                params[GENDER_INDEX] == null) {
            throw new IllegalArgumentException(Constants.INVALID_INPUT);
        }

        String name = params[NAME_INDEX];
        int age = Integer.parseInt(params[AGE_INDEX]);
        String gender = params[GENDER_INDEX];

        switch (animal) {
        case Constants.DOG:
            return new Dog(name, age, gender, Constants.DOG);
        case Constants.CAT:
            return new Cat(name, age, gender, Constants.CAT);
        case Constants.FROG:
            return new Frog(name, age, gender, Constants.FROG);
        case Constants.KITTEN:
            return new Kitten(name, age, Constants.FEMALE, Constants.KITTEN);
        case Constants.TOMCAT:
            return new Tomcat(name, age, Constants.MALE, Constants.TOMCAT);
        default:
            throw new IllegalArgumentException(Constants.INVALID_INPUT);
        }
    }
}
