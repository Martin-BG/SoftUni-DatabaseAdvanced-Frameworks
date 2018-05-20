package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderItemsWrapperDTO {
    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> items;

    public OrderItemsWrapperDTO() {
        this.items = new ArrayList<>();
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}
