package app.model.enums;

public enum Size {
    SMALL(0), MEDIUM(1), LARGE(2);

    private int value;

    Size(int i) {
        value = i;
    }

    public int getValue() {
        return value;
    }
}
