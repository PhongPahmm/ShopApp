package spring.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_payment")
public class OrderPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY)
    Order order;

    @Column(name = "payment_method")
    PaymentMethod paymentMethod;

    @Column(name = "payment_status")
    PaymentStatus paymentStatus;

    @Column(name = "note")
    String note;

    @Column(name = "payment_date")
    LocalDate paymentDate;

    @Column(name = "amount_paid")
    double amountPaid;
}
