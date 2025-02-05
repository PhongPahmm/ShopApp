package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
