package sowndv02.learning.hello.controller;

@FunctionalInterface // Interface chỉ có duy nhất 1 abstract method
public interface Printable {

    void print(int a);

    default void display(String name) {
        System.out.println("Name: " + name);
    }
}