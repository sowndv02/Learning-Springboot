package sowndv02.learning.sample.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import sowndv02.learning.sample.model.Student;

@Controller
public class HelloController {
	@GetMapping("hello")
	public String hello(ModelMap model) {
		model.addAttribute("message", "<h3>Hello Thymeleaf</h3>");
		return "hello";
	}

	@GetMapping()
	public String home(ModelMap model) {
		model.addAttribute("message", "Thymeleaf attribute");
		Student student = new Student();
		student.setName("Son");
		student.setStudentId("1");
		model.addAttribute("student", student);
		return "index";
	}

	@GetMapping("order/{orderId}/detail/{detailId}")
	public String detail(ModelMap model, @PathVariable int orderId, @PathVariable int detailId) {

		model.addAttribute("orderId", orderId);
		model.addAttribute("detailId", detailId);

		return "detail";
	}

	@GetMapping("fragment")
	public String fragment() {
		return "useFragments";
	}

	@GetMapping("rank")
	public String rank(ModelMap model) {
		Student student = new Student();
		student.setStudentId("01");
		student.setName("NguyenVanA");
		student.setGpa(10);
		student.setGender(true);

		model.addAttribute("student", student);
		return "student";
	}

	@GetMapping("list")
	public String list(ModelMap model) {
		List<Student> list = new ArrayList<>();

		list.add(new Student("1", "A", true, 8, new Date(2002, 11, 11)));
		list.add(new Student("2", "B", false, 6, new Date(2002, 11, 11)));
		list.add(new Student("3", "C", true, 10, new Date(2002, 11, 11)));
		list.add(new Student("4", "D", false, 4, new Date(2002, 11, 11)));
		list.add(new Student("5", "E", true, 5, new Date(2002, 11, 11)));
		list.add(new Student("6", "F", false, 9, new Date(2002, 11, 11)));
		list.add(new Student("7", "G", false, 8, new Date(2002, 11, 11)));

		model.addAttribute("students", list);

		return "studentList";
	}

}
