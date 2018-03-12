package pr05_say_hello_extend;

import pr05_say_hello_extend.contracts.Person;
import pr05_say_hello_extend.model.Bulgarian;
import pr05_say_hello_extend.model.Chinese;
import pr05_say_hello_extend.model.European;

import java.util.ArrayList;
import java.util.List;

class SeyHelloExtendDemo {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();


        persons.add(new Bulgarian("Gosho"));
        persons.add(new European("Gosho"));
        persons.add(new Chinese("Gosho"));

        for (Person person : persons) {
            print(person);
        }
    }

    private static void print(Person person) {
        person.sayHello();
    }
}
