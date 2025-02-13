package spring.shopapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.shopapp.models.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    boolean existsByToken(String token);
    Optional<Token> findByToken(String token);
}

