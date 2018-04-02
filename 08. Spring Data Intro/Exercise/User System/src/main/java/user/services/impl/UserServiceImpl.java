package user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.models.entities.User;
import user.repositories.UserRepository;
import user.services.api.UserService;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsersByEmailProvider(final String provider) {
        return this.userRepository.findAllByEmailEndingWith(provider);
    }

    @Override
    public void deactivateInactiveUsers(final Date date) {
        this.userRepository
                .findAllByLastTimeLoggedInBefore(date)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public void save(final User user) {
        this.userRepository.save(user);
    }
}
