package product_shop.model.dto.xml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import product_shop.model.dto.binding.CategoryDto;

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
@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesCategoryDto implements Serializable {

    @XmlElement(name = "category")
    private Set<CategoryDto> categories;

    public CategoriesCategoryDto() {
        this.categories = new HashSet<>();
    }
}
