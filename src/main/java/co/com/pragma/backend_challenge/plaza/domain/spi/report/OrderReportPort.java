package co.com.pragma.backend_challenge.plaza.domain.spi.report;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;

public interface OrderReportPort {
    void sendNewOrderReport(Order order);
    void addEmployeeToOrderLog(Long orderId, String employeeId);
    void addNewStateToOrderLog(Long orderId, OrderState state);
}
