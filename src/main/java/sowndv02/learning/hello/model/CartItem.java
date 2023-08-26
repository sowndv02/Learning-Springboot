package sowndv02.learning.hello.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
}
