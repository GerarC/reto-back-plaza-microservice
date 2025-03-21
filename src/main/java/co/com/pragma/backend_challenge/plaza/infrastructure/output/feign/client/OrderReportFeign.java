package co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.client;

import co.com.pragma.backend_challenge.plaza.domain.util.enums.OrderState;
import co.com.pragma.backend_challenge.plaza.infrastructure.configuration.feign.FeignClientConfiguration;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.dto.request.NewOrderLogRequest;
import co.com.pragma.backend_challenge.plaza.infrastructure.output.feign.util.FeignConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = FeignConstants.ORDER_REPORT_CLIENT,
        url = "${mall.traceability.base-url}/order-logs" ,
        configuration = FeignClientConfiguration.class
)
public interface OrderReportFeign {
    @PostMapping
    public void saveNewOrderLog(@RequestBody NewOrderLogRequest request);

    @PatchMapping("/orders/{orderId}/state")
    public void addNewState(@PathVariable Long orderId, @RequestParam OrderState add);

    @PatchMapping("/orders/{orderId}/assigned-employee")
    public void addAssignedEmployee(@PathVariable Long orderId, @RequestParam String add);
}
