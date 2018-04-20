package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    private ModelParser modelParser;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        Date date = new Date();
        try {
            date = sdf.parse(dto.getDate());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        Employee employee = null;
        employee = this.employeeRepository.findByName(dto.getEmployee());
        if (employee == null) {
            throw new IllegalArgumentException();
        }

        Order order = new Order();
        order.setCustomer(dto.getCustomer());
        order.setDate(date);
        order.setOrderType(OrderType.valueOf(dto.getType()));
        order.setEmployee(employee);

        BigDecimal orderTotalPrice = new BigDecimal(0);
        for (OrderItemXMLImportDTO orderItemXMLImportDTO : dto.getItems()) {
            Item item = null;
            item = this.itemsRepository.findByName(orderItemXMLImportDTO.getName());
            if (item == null) {
                throw new IllegalArgumentException();
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setQuantity(orderItemXMLImportDTO.getQuantity());
            orderItem.setOrder(order);
            order.getItems().add(orderItem);
            orderTotalPrice = orderTotalPrice.add(item.getPrice().multiply(new BigDecimal(orderItemXMLImportDTO.getQuantity())));
        }
        order.setTotalPrice(orderTotalPrice);
        Set<ConstraintViolation<Order>> constraintViolationSet = validator.validate(order);
        if (constraintViolationSet.size() != 0) {
            throw new IllegalArgumentException();
        }
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        EmployeeOrdersJSONExportDTO exportDTO = new EmployeeOrdersJSONExportDTO();
        List<Order> orderList = this.orderRepository
                .getOrdersByEmployeeNameAndOrderType(employeeName, OrderType.valueOf(orderType));
        for (Order order : orderList) {
            this.calculateOrderPrice(order);
        }
        this.sortByPrice(orderList);
        exportDTO.setEmployeeName(employeeName);
        List<OrderJSONExportDTO> ordersExportDtos = this.mapOrderJSONExportDTOItems(orderList);
        exportDTO.setOrders(ordersExportDtos);
        return exportDTO;
    }

    private void sortByPrice(List<Order> items) {
        items.sort((o1, o2) -> {
            int equality = o2.getTotalPrice().compareTo(o1.getTotalPrice());
            int itemsCountEquality = Integer.compare(o2.getItems().size(), o1.getItems().size());
            return equality != 0 ? equality : itemsCountEquality;
        });
    }

    private List<OrderJSONExportDTO> mapOrderJSONExportDTOItems(List<Order> orderList) {
        List<OrderJSONExportDTO> ordersExportDtos = new ArrayList<>();
        for (Order order : orderList) {
            OrderJSONExportDTO orderJSONExportDTO = new OrderJSONExportDTO();
            orderJSONExportDTO.setCustomer(order.getCustomer());
            ItemJSONExportDTO[] itemJSONExportDTOS = new ItemJSONExportDTO[order.getItems().size()];
            int i = 0;
            for (OrderItem orderItem : order.getItems()) {
                ItemJSONExportDTO dto = new ItemJSONExportDTO();
                dto.setName(orderItem.getItem().getName());
                dto.setPrice(orderItem.getItem().getPrice());
                dto.setQuantity(orderItem.getQuantity());
                itemJSONExportDTOS[i] = dto;
                i++;
            }
            orderJSONExportDTO.setItems(itemJSONExportDTOS);
            ordersExportDtos.add(orderJSONExportDTO);
        }
        return ordersExportDtos;
    }

    private void calculateOrderPrice(Order order) {
        BigDecimal orderTotalPrice = new BigDecimal(0);
        for (OrderItem orderItem : order.getItems()) {
            Item item = orderItem.getItem();
            int quantity = orderItem.getQuantity();
            BigDecimal price = item.getPrice().multiply(BigDecimal.valueOf(quantity));
            orderTotalPrice = orderTotalPrice.add(price);
        }
        order.setTotalPrice(orderTotalPrice);
    }
}
