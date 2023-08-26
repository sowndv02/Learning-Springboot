package sowndv02.learning.hello.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import sowndv02.learning.hello.model.Product;
import sowndv02.learning.hello.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    public static List<Product> list = new ArrayList<>();
    static {
        list.add(new Product(1, "Product 1", 100, 900, "Nothing"));
        list.add(new Product(2, "Product 2", 100, 400, "Nothing"));
        list.add(new Product(3, "Product 3", 100, 320, "Nothing"));
        list.add(new Product(4, "Product 4", 100, 120, "Nothing"));
        list.add(new Product(5, "Product 5", 100, 520, "Nothing"));
        list.add(new Product(6, "Product 6", 100, 850, "Nothing"));
    }

    @Override
    public void add(Product product) {
        list.add(product);
    }

    @Override
    public void remove(int productId) {
        list.removeIf(item -> item.getProductId() == productId);
    }

    @Override
    public List<Product> findAll() {
        return list;
    }

    @Override
    public Product findById(int productId) {
        Optional<Product> opt = list.stream().filter(item -> item.getProductId() == productId).findFirst();
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < list.size(); i++) {
            Product item = list.get(i);
            if (item.getProductId() == product.getProductId()) {
                list.set(i, product);
                break;
            }
        }
    }

}
