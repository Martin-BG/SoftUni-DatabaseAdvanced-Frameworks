package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesFrequentItemsXMLExportDTO {
    @XmlElement(name = "category")
    private List<CategoryExportDTO> categoriesWithMostFrequentItem;

    public CategoriesFrequentItemsXMLExportDTO() {
        this.categoriesWithMostFrequentItem = new ArrayList<>();
    }

    public List<CategoryExportDTO> getCategoriesWithMostFrequentItem() {
        return categoriesWithMostFrequentItem;
    }

    public void setCategoriesWithMostFrequentItem(List<CategoryExportDTO> categoriesWithMostFrequentItem) {
        this.categoriesWithMostFrequentItem = categoriesWithMostFrequentItem;
    }
}
