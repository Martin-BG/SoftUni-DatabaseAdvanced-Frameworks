package app.services.user;

import app.models.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(final User user) {
        if (this.userRepository.findByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException("User with that name already exists");
        }

        this.userRepository.save(user);
    }
}
