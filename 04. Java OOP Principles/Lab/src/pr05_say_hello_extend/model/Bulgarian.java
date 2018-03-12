package pr05_say_hello_extend.model;

public final class Bulgarian extends AbstractPerson {

    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public void sayHello() {
        System.out.println("Здравей");
    }

}
