package spring.shopapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "order_id")
    int orderId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Product product;
    @Column(name = "price")
    float price;
    @Column(name = "number_of_products")
    int numberOfProducts;
    @Column(name = "total_money")
    float totalMoney;
    @Column(name = "color")
    String color;
}
