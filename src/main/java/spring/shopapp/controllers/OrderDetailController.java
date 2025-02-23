package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.ApiResponse;
import spring.shopapp.dtos.request.OrderDetailCreationRequest;
import spring.shopapp.dtos.response.OrderDetailResponse;
import spring.shopapp.services.order_detail.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("/order-detail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailController {
    OrderDetailService orderDetailService;

    @PostMapping
    public ApiResponse<OrderDetailResponse> createOrder(@RequestBody OrderDetailCreationRequest request){
        return ApiResponse.<OrderDetailResponse>builder()
                .data(orderDetailService.createOrderDetail(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderDetailResponse>> getOrdersDetail() {
        return ApiResponse.<List<OrderDetailResponse>>builder()
                .data(orderDetailService.getOrderDetails())
                .message("Successfully retrieved orders detail")
                .build();
    }
}
