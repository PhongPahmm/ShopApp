
package spring.shopapp.dtos.request;

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
public class OrderPaymentRequest {
    PaymentMethod paymentMethod;
    PaymentStatus paymentStatus;
    String note;
    LocalDateTime paymentDate;
    float amountPaid;
}
