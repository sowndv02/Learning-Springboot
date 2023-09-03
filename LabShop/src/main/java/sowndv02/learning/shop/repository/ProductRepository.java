package sowndv02.learning.shop.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sowndv02.learning.shop.model.InventoryReport;
import sowndv02.learning.shop.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("select o from Product o where o.price >= ?1 and o.price <= ?2")
	public Page<Product> searchByPrice(Double min, Double max, Pageable pageable);
	
	public Page<Product> findByPriceBetween(Double min, Double max, Pageable pageable);
	
	@Query(name="searchByName")
	public Page<Product> searchByName(String name, Pageable pageable);
	
	@Query("Select new sowndv02.learning.shop.model.InventoryReport(o.category, sum(o.price), sum(o.quantity)) From Product o Group by o.category")
	public Page<InventoryReport> reportInventory(Pageable pageable);
	
}
