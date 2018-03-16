package pr04_say_hello.model;

import pr04_say_hello.contracts.Person;

public final class European implements Person {

    private final String name;

    public European(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String sayHello() {
        return "Hello";
    }
}
