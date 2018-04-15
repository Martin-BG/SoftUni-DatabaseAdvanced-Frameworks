package product_shop.persistance.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product_shop.model.dto.binding.UserDto;
import product_shop.model.dto.view.UserFirstAndLastNamesAndSoldProductsDto;
import product_shop.model.entity.User;
import product_shop.persistance.repository.UserRepository;
import product_shop.persistance.service.api.UserService;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAll(final UserDto[] usersDto) {
        User[] users = this.modelMapper.map(usersDto, User[].class);
        this.userRepository.saveAll(Arrays.asList(users));

        for (int i = 0; i < users.length * 2; i++) {
            User user = this.userRepository.getRandomEntity();
            User friend = this.userRepository.getRandomEntity();
            if (user != null && friend != null && !user.equals(friend)) {
                user.getFriends().add(friend);
                friend.getFriends().add(user);
            }
        }
    }

    @Override
    public List<UserFirstAndLastNamesAndSoldProductsDto> getSuccessfulSellers() {
        final List<User> allBySellContainsProduct_buyer = this.userRepository.getAllBySellContainsProduct_Buyer();
        System.out.println();
        return this.userRepository.getAllBySellContainsProduct_Buyer()
                .stream()
                .sorted(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName))
                .map(user -> this.modelMapper.map(user, UserFirstAndLastNamesAndSoldProductsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getRandom() {
        return this.userRepository.getRandomEntity();
    }

    @Override
    public User getFromId(final Long id) {
        return this.userRepository.getOne(id);
    }
}
