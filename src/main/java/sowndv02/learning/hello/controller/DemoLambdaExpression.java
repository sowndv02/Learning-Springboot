package sowndv02.learning.hello.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sowndv02.learning.hello.model.CartItem;

public class DemoLambdaExpression {
    public static void main(String[] args) {

        // ArrayList with lambda Expression
        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        System.out.println("Using for ");
        for (String string : list) {
            System.out.println(string);
        }

        System.out.println("Lambda Expression");
        list.forEach(x -> System.out.println(x.toString()));

        System.out.println("Other");
        list.forEach(System.out::println); // In ra theo cách khác

        List<CartItem> listItem = new ArrayList<>();
        listItem.addAll(Arrays.asList(
                new CartItem(1, "A", 1, 1231),
                new CartItem(2, "B", 1, 1231),
                new CartItem(3, "C", 1, 1231),
                new CartItem(4, "D", 1, 1231)));

        listItem.forEach(
                item -> System.out.println("ID: " + item.getProductId() + " - Name: " +
                        item.getProductName()));

        // Map with Lambda Expression
        Map<String, CartItem> map = new HashMap<>();
        map.put("K01", new CartItem(1, "A", 1, 1231));
        map.put("K02", new CartItem(2, "B", 1, 1231));

        for (Entry<String, CartItem> entry : map.entrySet()) {
            String key = entry.getKey();
            CartItem value = entry.getValue();

            System.out.println(String.format("%s - %s - %s", key, value.getProductId(),
                    value.getProductName()));
        }

        map.forEach((key, value) -> System.out
                .println(String.format("%s - %s - %s", key, value.getProductId(),
                        value.getProductName())));

        // Comparator
        listItem.sort(new Comparator<CartItem>() {
            @Override
            public int compare(CartItem c1, CartItem c2) {
                return c1.getProductId() - c2.getProductId();
            }
        });

        listItem.sort((c1, c2) -> c1.getProductId() - c2.getProductId());

        // Interface
        Printable obj = new Printable() {
            @Override
            public void print(int a) {
                System.out.println("a: " + a);
            }
        };

        obj.print(10);

        Printable p1 = (a) -> System.out.println("a == " + a);
        p1.print(20);

        compare(a -> System.out.println("a: " + a));
    }

    public static void compare(Printable p) {
        p.print(111);
    }
}
