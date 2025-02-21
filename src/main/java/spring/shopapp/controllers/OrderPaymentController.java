package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.OrderPaymentRequest;
import spring.shopapp.dtos.response.OrderPaymentResponse;
import spring.shopapp.services.order.OrderPaymentService;

@RestController
@RequestMapping("/order-payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderPaymentController {
    OrderPaymentService orderPaymentService;

    @PostMapping
    public ApiResponse<OrderPaymentResponse> createOrderPayment(OrderPaymentRequest request){
        return ApiResponse.<OrderPaymentResponse>builder()
                .data(orderPaymentService.createOrderPayment(request))
                .build();
    }

    @GetMapping("/{orderPaymentId}")
    public ApiResponse<OrderPaymentResponse> getOrderPayment(@PathVariable int orderPaymentId) {
        return ApiResponse.<OrderPaymentResponse>builder()
                .data(orderPaymentService.getOrderPayment(orderPaymentId))
                .message("Order payment found")
                .build();
    }
}
