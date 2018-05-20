package hiberspring.dtos.imports.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsImportFromXmlDto {

    @XmlElement(name = "product")
    private List<ProductImportFromXmlDto> productImportFromXmlDtos;

    public ProductsImportFromXmlDto() {
    }

    public List<ProductImportFromXmlDto> getProductImportFromXmlDtos() {
        return this.productImportFromXmlDtos;
    }

    public void setProductImportFromXmlDtos(List<ProductImportFromXmlDto> productImportFromXmlDtos) {
        this.productImportFromXmlDtos = productImportFromXmlDtos;
    }
}
