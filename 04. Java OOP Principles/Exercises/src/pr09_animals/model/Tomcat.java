package pr09_animals.model;


import pr09_animals.constants.Constants;

public class Tomcat extends Cat {

    public Tomcat(String name, int age, String gender, String type) {
        super(name, age, gender, type);
    }

    @Override
    public String produceSound() {
        return Constants.TOMCAT_SOUND;
    }
}
