package pr05_border_control.model;

import pr05_border_control.contracts.Identable;

public abstract class AbstractIdentable implements Identable {

    private final String id;

    public AbstractIdentable(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

}
