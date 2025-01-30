package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.ProductImage;

import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    Optional<ProductImage> findProductImagesById(int productId);
}
