package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-popular-item")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class MostPopularItemDTO {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "total-made")
    private Double totalMade;

    @XmlElement(name = "times-sold")
    private Integer timesSold;

    public MostPopularItemDTO() {
    }

    public MostPopularItemDTO(String name, Double totalMade, Integer timesSold) {
        this.name = name;
        this.totalMade = totalMade;
        this.timesSold = timesSold;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalMade() {
        return this.totalMade;
    }

    public void setTotalMade(Double totalMade) {
        this.totalMade = totalMade;
    }

    public Integer getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(Integer timesSold) {
        this.timesSold = timesSold;
    }
}
