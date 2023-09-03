package sowndv02.learning.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class InventoryReport {
	private Category category;
	private Double priceSum;
	private Long quantityCount;
	
}
