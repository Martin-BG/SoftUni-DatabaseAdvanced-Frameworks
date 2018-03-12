package pr04_say_hello.model;

import pr04_say_hello.contracts.Person;

public final class Bulgarian implements Person {

    private final String name;

    public Bulgarian(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sayHello() {
        System.out.println("Здравей");
    }

}
