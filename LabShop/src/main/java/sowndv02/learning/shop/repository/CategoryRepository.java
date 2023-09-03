package sowndv02.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sowndv02.learning.shop.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
