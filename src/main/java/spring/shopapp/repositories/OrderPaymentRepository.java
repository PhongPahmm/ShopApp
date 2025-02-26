package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.OrderPayment;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Integer> {
}
