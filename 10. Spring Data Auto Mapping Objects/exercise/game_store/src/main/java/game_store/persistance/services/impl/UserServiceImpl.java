package game_store.persistance.services.impl;

import game_store.model.dto.binding.RegisterUserDto;
import game_store.model.dto.view.GameTitleViewDto;
import game_store.model.dto.view.LoggedInUserDto;
import game_store.model.entities.Game;
import game_store.model.entities.User;
import game_store.model.enums.Role;
import game_store.model.utils.ObjectMapper;
import game_store.persistance.repositories.UserRepository;
import game_store.persistance.services.api.GameService;
import game_store.persistance.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final GameService gameService;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final GameService gameService) {
        this.userRepository = userRepository;
        this.gameService = gameService;
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
    public Set<GameTitleViewDto> getOwnedGamesTitle(Long id) {
        if (id != null) {
            return this.userRepository
                    .findById(id)
                    .map(user -> user.getGames()
                            .stream()
                            .map(game -> ObjectMapper.getInstance().map(game, GameTitleViewDto.class))
                            .collect(Collectors.toSet())).orElse(new HashSet<>());
        }

        return new HashSet<>();
    }

    @Override
    public Set<GameTitleViewDto> getCartGamesTitle(Long id) {
        if (id != null) {
            return this.userRepository
                    .findById(id)
                    .map(user -> user.getShoppingCart()
                            .stream()
                            .map(game -> ObjectMapper.getInstance().map(game, GameTitleViewDto.class))
                            .collect(Collectors.toSet())).orElse(new HashSet<>());
        }

        return new HashSet<>();
    }

    @Override
    public void addGameToUserCart(final Long id, final GameTitleViewDto gameTitleViewDto) {
        Game game = this.gameService.getGameFromTitle(gameTitleViewDto.getTitle());
        if (game != null) {
            this.userRepository
                    .findById(id)
                    .ifPresent(user -> user.getShoppingCart().add(game));
        }
    }

    @Override
    public void removeGameFromUserCart(final Long id, final GameTitleViewDto gameTitleViewDto) {
        Game game = this.gameService.getGameFromTitle(gameTitleViewDto.getTitle());
        if (game != null) {
            this.userRepository
                    .findById(id)
                    .ifPresent(user -> user.getShoppingCart().remove(game));
        }
    }

    @Override
    public void buyAllGamesFromUserCart(final Long id) {
        this.userRepository
                .findById(id)
                .ifPresent(user -> {
                    user.getShoppingCart().forEach(game -> user.getGames().add(game));
                    user.getShoppingCart().clear();
                });
    }
}
