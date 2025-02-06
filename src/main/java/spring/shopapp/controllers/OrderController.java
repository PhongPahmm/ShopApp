package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.OrderCreationRequest;
import spring.shopapp.dtos.response.OrderResponse;
import spring.shopapp.services.order.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;
    @GetMapping
    public ApiResponse<List<OrderResponse>> getAllOrders() {
        return ApiResponse.<List<OrderResponse>>builder()
                .data(orderService.getAllOrders())
                .message("Successfully retrieved all orders")
                .build();
    }
    @GetMapping("/{orderId}")
    public ApiResponse<OrderResponse> getOrderById(@PathVariable("orderId") int orderId) {
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.getOrderById(orderId))
                .message("Successfully retrieved order by id")
                .build();
    }

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderCreationRequest request){
        return ApiResponse.<OrderResponse>builder()
                .data(orderService.createOrder(request))
                .message("Successfully created order")
                .build();
    }
}
