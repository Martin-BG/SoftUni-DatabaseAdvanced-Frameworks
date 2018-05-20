package hiberspring.dtos.views.branch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchViewDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "town")
    private String townName;

    @XmlAttribute(name = "town_clients")
    private Long count;

    public BranchViewDto() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return this.townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        if (count == null) {
            this.count = 0L;
        } else {
            this.count = count;
        }
    }
}
