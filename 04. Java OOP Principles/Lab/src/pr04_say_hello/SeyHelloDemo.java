package pr04_say_hello;

import pr04_say_hello.contracts.Person;
import pr04_say_hello.model.Bulgarian;
import pr04_say_hello.model.Chinese;
import pr04_say_hello.model.European;

import java.util.ArrayList;
import java.util.List;

class SeyHelloDemo {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Bulgarian("Pesho"));
        persons.add(new European("Pesho"));
        persons.add(new Chinese("Pesho"));

        for (Person person : persons) {
            print(person);
        }
    }

    private static void print(Person person) {
        person.sayHello();
    }
}
