package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.adapter;

import co.com.pragma.backend_challenge.plaza.domain.model.order.Order;
import co.com.pragma.backend_challenge.plaza.domain.spi.report.OrderReportPort;
import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client.OrderReportFeign;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.mapper.request.OrderLogRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLogFeignAdapter implements OrderReportPort {
    private final OrderReportFeign orderReportFeign;
    private final OrderLogRequestMapper orderLogRequestMapper;

    @Override
    public void sendNewOrderReport(Order order) {
        NewOrderLogRequest newLog = orderLogRequestMapper.toRequest(order);
        orderReportFeign.saveNewOrderLog(newLog);
    }

    @Override
    public void addEmployeeToOrderLog(Long orderId, String employeeId) {
        orderReportFeign.addAssignedEmployee(orderId, employeeId);
    }

    @Override
    public void addNewStateToOrderLog(Long orderId, OrderState state) {
        orderReportFeign.addNewState(orderId, state);
    }
}
