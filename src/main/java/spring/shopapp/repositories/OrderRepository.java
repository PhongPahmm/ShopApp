package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.shopapp.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
