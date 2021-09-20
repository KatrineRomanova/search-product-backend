package search.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import search.product.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

}