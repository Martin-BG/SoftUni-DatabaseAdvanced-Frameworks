package product_shop.model.dto.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import product_shop.model.dto.binding.UserDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersUserDto implements Serializable {

    @XmlElement(name = "user")
    private Set<UserDto> users;

    public UsersUserDto() {
        this.users = new HashSet<>();
    }
}
