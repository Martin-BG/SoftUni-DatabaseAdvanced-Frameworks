package pr06_birthday_celebrations.model;

import pr06_birthday_celebrations.contracts.Birthable;
import pr06_birthday_celebrations.contracts.Nameable;

public class Citizen extends AbstractIdentable implements Nameable, Birthable {

    private final String name;
    private int age;
    private String birthday;

    public Citizen(String id, String name, int age, String birthday) {
        super(id);
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public String getBirthday() {
        return this.birthday;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
