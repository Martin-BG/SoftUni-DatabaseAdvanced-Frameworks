package pr01_define_an_interface_person.model;

import pr01_define_an_interface_person.contracts.Person;

public class Citizen implements Person {

    private String name;
    private int age;

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
}
