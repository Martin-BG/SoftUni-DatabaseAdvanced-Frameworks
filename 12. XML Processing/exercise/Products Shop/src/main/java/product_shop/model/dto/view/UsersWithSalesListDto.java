package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class UsersWithSalesListDto implements Serializable {

    private Integer usersCount;

    private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;

    public UsersWithSalesListDto() {
        users = new ArrayList<>();
    }
}
