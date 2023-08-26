package sowndv02.learning.hello.controller;

import java.util.Collection;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import sowndv02.learning.hello.model.CartItem;
import sowndv02.learning.hello.model.Product;
import sowndv02.learning.hello.services.ProductService;
import sowndv02.learning.hello.services.ShoppingCartService;

@Controller
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("list")
    public String list(Model model) {
        Collection<CartItem> cartItems = shoppingCartService.getCartItems();
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", shoppingCartService.getAmount());
        model.addAttribute("NoOfItems", shoppingCartService.getCount());
        return "shoppingCart/list";
    }

    @GetMapping("add/{productId}")
    public String add(@PathVariable("productId") int productId) {
        Product product = productService.findById(productId);
        if (product != null) {
            CartItem item = new CartItem();
            BeanUtils.copyProperties(product, item); // Cho phép copy thuộc tính từ product sang item
            item.setQuantity(1);
            shoppingCartService.add(item);
        }
        return "redirect:/shoppingCart/list";
    }

    @GetMapping("remove/{productId}")
    public String remove(@PathVariable("productId") int productId) {
        shoppingCartService.remove(productId);
        return "redirect:/shoppingCart/list";
    }

    @PostMapping("update")
    public String update(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity) {
        shoppingCartService.update(productId, quantity);
        return "redirect:/shoppingCart/list";
    }

    @GetMapping("clear")
    public String clear() {
        return "redirect:/shoppingCart/list";
    }
}
