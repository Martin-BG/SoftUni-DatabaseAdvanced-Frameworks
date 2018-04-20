package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "most-popular-item")
    private MostPopularItemDTO mostPopularItemDTO;

    public CategoryExportDTO() {
    }

    public CategoryExportDTO(String name, MostPopularItemDTO mostPopularItemDTO) {
        this.name = name;
        this.mostPopularItemDTO = mostPopularItemDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getMostPopularItemDTO() {
        return mostPopularItemDTO;
    }

    public void setMostPopularItemDTO(MostPopularItemDTO mostPopularItemDTO) {
        this.mostPopularItemDTO = mostPopularItemDTO;
    }
}
