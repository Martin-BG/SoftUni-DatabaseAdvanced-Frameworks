package pr05_say_hello_extend.model;

import pr05_say_hello_extend.contracts.Person;

public abstract class AbstractPerson implements Person {

    private final String name;

    public AbstractPerson(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
