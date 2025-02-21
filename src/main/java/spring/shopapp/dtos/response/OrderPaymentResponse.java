package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import spring.shopapp.models.PaymentMethod;
import spring.shopapp.models.PaymentStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderPaymentResponse {
    int order_id;
    PaymentMethod payment_method;
    PaymentStatus payment_status;
    String note;
    LocalDateTime payment_date;
    float amount_paid;
}
