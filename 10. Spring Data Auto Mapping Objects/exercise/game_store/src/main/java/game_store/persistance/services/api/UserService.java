package game_store.persistance.services.api;

import game_store.model.dto.binding.RegisterUserDto;
import game_store.model.dto.view.LoggedInUserDto;
import game_store.model.entities.User;

public interface UserService {

    User persist(RegisterUserDto registerUserDto);

    LoggedInUserDto logInUser(final String email, final String password);

    boolean userExists(String userName);
}
