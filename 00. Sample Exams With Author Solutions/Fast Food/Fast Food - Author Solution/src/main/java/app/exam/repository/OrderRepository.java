package app.exam.repository;

import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> getOrdersByEmployeeNameAndOrderType(String employeeName, OrderType type);
}
