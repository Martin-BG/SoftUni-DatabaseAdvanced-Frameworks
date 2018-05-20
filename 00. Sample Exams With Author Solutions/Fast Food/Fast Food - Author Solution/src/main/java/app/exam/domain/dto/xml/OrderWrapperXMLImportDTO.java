package app.exam.domain.dto.xml;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "orders")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class OrderWrapperXMLImportDTO implements Serializable {
    @XmlElement(name = "order")
    private List<OrderXMLImportDTO> orders;

    public OrderWrapperXMLImportDTO() {
        this.orders = new ArrayList<>();
    }

    public List<OrderXMLImportDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderXMLImportDTO> orders) {
        this.orders = orders;
    }
}
