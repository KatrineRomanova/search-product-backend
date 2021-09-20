package search.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import search.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}