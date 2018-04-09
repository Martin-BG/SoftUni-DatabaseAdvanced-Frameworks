package game_store.persistance.services.impl;

import game_store.model.dto.binding.RegisterUserDto;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.model.dto.view.LoggedInUserDto;
import game_store.model.entities.User;
import game_store.model.enums.Role;
import game_store.model.utils.ObjectMapper;
import game_store.persistance.repositories.UserRepository;
import game_store.persistance.services.api.UserService;
import game_store.store.LoggedUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User persist(final RegisterUserDto registerUserDto) {
        User user = ObjectMapper.getInstance().map(registerUserDto, User.class);

        if (this.userRepository.count() > 0L) {
            user.setRole(Role.USER);
        } else {
            user.setRole(Role.ADMIN);
        }

        return this.userRepository.save(user);
    }

    @Override
    public LoggedInUserDto logInUser(final String email, final String password) {
        User user = this.userRepository.findByEmailAndPassword(email, password);

        if (user != null) {
            return ObjectMapper.getInstance().map(user, LoggedInUserDto.class);
        }

        return null;
    }

    @Override
    public boolean userExists(final String email) {
        return this.userRepository.existsByEmailEquals(email);
    }


    @Override
    public List<GameTitleViewDto> getOwnedGamesTitle() {
        Long loggedUserId = LoggedUserRegister.getLoggedUserId();

        if (loggedUserId != null) {
            return this.userRepository
                    .findById(LoggedUserRegister.getLoggedUserId())
                    .map(user -> user.getGames()
                            .stream()
                            .map(game -> ObjectMapper.getInstance().map(game, GameTitleViewDto.class))
                            .collect(Collectors.toList())).orElse(null);
        }

        return null;
    }
}
