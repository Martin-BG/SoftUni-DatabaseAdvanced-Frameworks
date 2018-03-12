package pr05_say_hello_extend.contracts;

public interface Person {

    String getName();

    default void sayHello() {
        System.out.println("Hello");
    }
}
