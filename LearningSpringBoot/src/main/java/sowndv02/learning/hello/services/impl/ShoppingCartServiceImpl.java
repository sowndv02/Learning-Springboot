package sowndv02.learning.hello.services.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import sowndv02.learning.hello.model.CartItem;
import sowndv02.learning.hello.services.ShoppingCartService;

@Service
@SessionScope // Tồn tại trong session
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private Map<Integer, CartItem> map = new HashMap<>();

    public void add(CartItem item) {
        CartItem existedItem = map.get(item.getProductId());

        if (existedItem != null) {
            existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
        } else {
            map.put(item.getProductId(), item);
        }
    }

    public void remove(int productId) {
        map.remove(productId);
    }

    public Collection<CartItem> getCartItems() {
        return map.values();
    }

    public void clear() {
        map.clear();
    }

    public void update(int productId, int quantity) {
        CartItem item = map.get(productId);

        item.setQuantity(quantity);
        if (item.getQuantity() <= 0) {
            map.remove(productId);
        }
    }

    public double getAmount() {
        return map.values().stream().mapToDouble(item -> item.getQuantity() * item.getPrice()).sum();
    }

    public int getCount() {
        if (map.isEmpty())
            return 0;
        return map.values().size();
    }
}
