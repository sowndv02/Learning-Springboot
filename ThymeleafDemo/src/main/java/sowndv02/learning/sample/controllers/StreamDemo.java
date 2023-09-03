package sowndv02.learning.sample.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

import sowndv02.learning.sample.model.Employee;

public class StreamDemo {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 3, 4, 5);
		
		Stream<Integer> stream = list.stream();		
		
		stream.forEach(System.out::println);
		
		DemoObjectStream();
		DemoFillter();
	}
	
	public static void DemoObjectStream() {
		Employee e1 = new Employee("1", "A");
		Employee e2 = new Employee("2", "B");
		Employee e3 = new Employee("3", "C");
		Employee e4 = new Employee("4", "D");
		Employee e5 = new Employee("5", "E");
		Employee e6 = new Employee("6", "F");
		
		Stream<Employee> stream = Stream.of(e1, e2, e3, e4, e5, e6);
		
		stream.forEach(item -> {
			System.out.println(item.getId() + " - " + item.getName());
		});
	}
	
	public static void DemoFillter() {
		List<Integer> list = Arrays.asList(1, 3, 4, 5);
		
		Stream<Integer> stream = list.stream();
		
		Stream<Integer> fillterStream = stream.filter(item -> item % 2 == 0);
		stream.filter(item -> item % 2 == 0).forEach(System.out::println)
		fillterStream.forEach(System.out::println);
		
		List<Integer> newList = stream.filter(item -> item != 4).collect(Collector.toList()); // Tạo thành 1 list mới sử dụng collect
		newList.forEach(System.out::println);
		
	}
	
	// Như trong js
	public static void DemoMap() {
		
	}
	
}
