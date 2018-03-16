package pr04_say_hello.contracts;

public interface Person {

    String getName();

    default String sayHello() {
        return "Hello";
    }
}
