package product_shop.model.dto.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersWithSalesListDto implements Serializable {

    @XmlAttribute(name = "count")
    private Integer usersCount;

    private List<UserFirstLastNamesAgeAndSoldProductsNameAndPriceDto> users;

    public UsersWithSalesListDto() {
        users = new ArrayList<>();
    }
}
