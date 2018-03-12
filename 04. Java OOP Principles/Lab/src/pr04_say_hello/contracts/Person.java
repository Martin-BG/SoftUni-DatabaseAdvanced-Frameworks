package pr04_say_hello.contracts;

public interface Person {

    String getName();

    default void sayHello() {
        System.out.println("Hello");
    }
}
