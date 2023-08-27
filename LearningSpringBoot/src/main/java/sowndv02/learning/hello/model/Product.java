package sowndv02.learning.hello.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private int productId;
    private String productName;
    private int quantity;
    private double price;
    private String description;

}
