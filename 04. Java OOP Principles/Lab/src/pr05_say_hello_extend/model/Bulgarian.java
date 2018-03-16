package pr05_say_hello_extend.model;

import pr05_say_hello_extend.contracts.Person;

public final class Bulgarian extends BasePerson implements Person {

    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return "Здравей";
    }
}
