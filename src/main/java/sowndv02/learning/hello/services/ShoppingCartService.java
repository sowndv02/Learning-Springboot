package sowndv02.learning.hello.services;

import java.util.Collection;

import sowndv02.learning.hello.model.CartItem;

public interface ShoppingCartService {
    int getCount();

    double getAmount();

    void update(int productId, int quantity);

    void clear();

    Collection<CartItem> getCartItems();

    void remove(int productId);

    void add(CartItem item);
}
