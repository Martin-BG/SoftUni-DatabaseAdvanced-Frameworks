package product_shop.persistance.service.api;

import product_shop.model.dto.binding.UserDto;
import product_shop.model.dto.view.UserFirstAndLastNamesAndSoldProductsDto;
import product_shop.model.entity.User;

import java.util.List;

public interface UserService {

    void saveAll(UserDto[] usersDto);

    User getRandom();

    User getFromId(Long id);

    List<UserFirstAndLastNamesAndSoldProductsDto> getSuccessfulSellers();
}
