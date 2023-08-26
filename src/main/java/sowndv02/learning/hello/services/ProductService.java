package sowndv02.learning.hello.services;

import java.util.List;

import sowndv02.learning.hello.model.Product;

public interface ProductService {
    void update(Product product);

    List<Product> findAll();

    void remove(int productId);

    void add(Product product);

    Product findById(int productId);
}
