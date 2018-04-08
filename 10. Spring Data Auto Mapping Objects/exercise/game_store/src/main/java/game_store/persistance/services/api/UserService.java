package game_store.persistance.services.api;

import game_store.model.dto.binding.RegisterUserDto;
import game_store.model.entities.User;

public interface UserService {

    User persist(RegisterUserDto registerUserDto);

    boolean userExists(String userName);
}
