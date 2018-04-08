package game_store.model.utils;

import org.modelmapper.ModelMapper;

public final class ObjectMapper {
    private static ModelMapper instance;

    private ObjectMapper() {
    }

    public static ModelMapper getInstance() {

        if (instance == null) {
            instance = new ModelMapper();
        }

        return instance;
    }
}
