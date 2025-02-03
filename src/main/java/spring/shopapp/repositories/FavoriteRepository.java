package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.Favorite;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    boolean existsByUserIdAndProductId(int userId, int productId);
    void deleteByUserIdAndProductId(int userId, int productId);
}
