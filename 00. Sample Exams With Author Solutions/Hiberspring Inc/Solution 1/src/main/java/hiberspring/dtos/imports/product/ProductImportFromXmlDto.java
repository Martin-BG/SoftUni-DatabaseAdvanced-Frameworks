package hiberspring.dtos.imports.product;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportFromXmlDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "clients")
    private Integer countOfClients;

    @XmlElement(name = "branch")
    private String branchName;

    public ProductImportFromXmlDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCountOfClients() {
        return this.countOfClients;
    }

    public void setCountOfClients(Integer countOfClients) {
        this.countOfClients = countOfClients;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
