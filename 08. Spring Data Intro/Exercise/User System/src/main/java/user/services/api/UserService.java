package user.services.api;

import user.models.entities.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    List<User> getAllUsersByEmailProvider(String provider);

    void deactivateInactiveUsers(Date date);

    void save(User user);

}
