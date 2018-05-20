package app.exam.controller;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class OrdersController {
    @Autowired
    private OrderService orderService;

    @Autowired
    @Qualifier(value = "XMLParser")
    private Parser xmlParser;

    @Autowired
    @Qualifier(value = "JSONParser")
    private Parser jsonParser;

    @Autowired
    private DefaultResourceLoader classLoader;

    public String importDataFromXML(String xmlContent) {
        OrderWrapperXMLImportDTO orders = new OrderWrapperXMLImportDTO();

        try {
            orders = this.xmlParser.read(OrderWrapperXMLImportDTO.class, xmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (OrderXMLImportDTO orderXMLImportDTO : orders.getOrders()) {
            try {
                this.orderService.create(orderXMLImportDTO);
                sb.append(String.format("Order for %s on %s added.",
                        orderXMLImportDTO.getCustomer(),
                        orderXMLImportDTO.getDate()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) throws IOException, JAXBException {
        EmployeeOrdersJSONExportDTO exportDTO =
                this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType);
        return this.jsonParser.write(exportDTO);
    }
}
