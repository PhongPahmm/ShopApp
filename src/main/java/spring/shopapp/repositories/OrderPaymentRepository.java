package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.shopapp.models.OrderPayment;

public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {
}
