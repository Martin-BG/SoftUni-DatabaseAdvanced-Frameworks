package pr06_birthday_celebrations.model;

public class Robot extends AbstractIdentable {

    private final String model;

    public Robot(String id, String model) {
        super(id);
        this.model = model;
    }
}
