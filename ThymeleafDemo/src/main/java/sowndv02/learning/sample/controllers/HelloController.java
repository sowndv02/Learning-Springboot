package sowndv02.learning.sample.controllers;

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
		Student student = new Student("1", "Sondv");
		model.addAttribute("student", student);
		return "index";
	}
	
	@GetMapping("order/{orderId}/detail/{detailId}")
	public String detail(ModelMap model,@PathVariable int orderId,@PathVariable int detailId) {
		
		model.addAttribute("orderId", orderId);
		model.addAttribute("detailId", detailId);
		
		return "detail";
	}
	
	@GetMapping("fragment")
	public String fragment() {
		return "useFragments";
	}
	
}
