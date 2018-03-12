package pr05_say_hello_extend.model;

public final class Chinese extends AbstractPerson {

    public Chinese(String name) {
        super(name);
    }

    @Override
    public void sayHello() {
        System.out.println("Ni Hao");
    }
}
