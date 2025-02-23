package spring.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    User user;
    @Column(name = "order_date")
    LocalDateTime orderDate;
    @Column(name = "status")
    OrderStatus orderStatus;
    @Column(name = "total_money")
    float totalMoney;
}
