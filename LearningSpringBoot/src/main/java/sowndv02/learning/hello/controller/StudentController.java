package sowndv02.learning.hello.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import sowndv02.learning.hello.model.Country;
import sowndv02.learning.hello.model.Student;

@Controller
@RequestMapping("students")

public class StudentController {

	@Autowired
	ServletContext app;

	@GetMapping("delete/{studentId}")
	public String delete(@PathVariable("studentId") String studentId) {
		System.out.println("Delete: " + studentId);
		return "students/list";
	}

	@GetMapping("viewDetail/{studentId}")
	public String viewDetail(@PathVariable("studentId") String studentId, Model model) {
		ArrayList<Student> students = (ArrayList) model.getAttribute("students");
		for (Student s : students) {
			if (s.getStudentId().equals(studentId)) {
				model.addAttribute("studentId", s.getStudentId());
				model.addAttribute("studentName", s.getStudentName());
			}

		}
		return "students/viewDetail";
	}

	// @ModelAttribute("students") // Khai báo model có tên là students
	// public List<Student> getStudents() {
	// List<Student> students = new ArrayList<>();
	// students.add(new Student("SE1", "Nguyen Van A"));
	// students.add(new Student("SE2", "Nguyen Van B"));
	// students.add(new Student("SE3", "Nguyen Van C"));
	// students.add(new Student("SE4", "Nguyen Van D"));

	// return students;
	// }

	// Version 1
	// @GetMapping("new")
	// public String newForm() { // Khai báo action có URL là new trả về view có tên
	// là students/new
	// return ("students/new");
	// }

	// Version 2: Spring Form
	@GetMapping("new")
	public String newForm(Model model) { // Khai báo action có URL là new trả về view có tên là students/new
		Student student = new Student();
		model.addAttribute("student", student);
		return ("students/new");
	}

	// Version 1
	// @PostMapping("saveOrUpdate")
	// public String saveOrUpdate(@RequestParam("studentId") String studentId,
	// @RequestParam("studentName") String studentName, Model model) {
	// // Lấy về 2 param của form gửi lên
	// System.out.println("StudentID: " + studentId);
	// System.out.println("StudentName: " + studentName);
	// model.addAttribute("studentId", studentId);
	// model.addAttribute("studentName", studentName);
	// return "students/viewDetail";
	// }

	// Version 2: Spring form
	@PostMapping("saveOrUpdate")
	// BindingResult khai báo ngay sau bean cần kiểm tra lỗi
	public String saveOrUpdate(@Validated @ModelAttribute("student") Student student, BindingResult result) {
		if (result.hasErrors()) {
			return "students/new";
		}
		if (!student.getImgFile().isEmpty()) {
			String path = app.getRealPath("/");
			try {
				student.setImgUrl(student.getImgFile().getOriginalFilename());
				String filePath = path + "/images/" + student.getImgUrl();
				student.getImgFile().transferTo(Path.of(filePath));
				student.setImgFile(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "students/viewDetail";
	}

	// Data fill to select tag
	@ModelAttribute("countries")
	public ArrayList<Country> getCountries() {
		ArrayList list = new ArrayList<>();
		list.add(new Country("HN", "Hà Nội"));
		list.add(new Country("BG", "Bắc Giang"));
		list.add(new Country("QN", "Quy Nhơn"));
		list.add(new Country("BD", "Bình Định"));
		return list;
	}

	// Data fill to Checkbox
	@ModelAttribute("hobbies")
	public Map<String, String> getHobbies() {
		Map<String, String> list = new HashMap<>();
		list.put("Nghe nhạc", "Rock");
		list.put("Chơi game", "FPS");
		list.put("Thể thao", "Đá bóng");
		return list;
	}

	// Data fill to Radio button
	@ModelAttribute("positions")
	public Map<String, String> getPositions() {
		Map<String, String> list = new HashMap<>();
		list.put("PM", "Project Manage");
		list.put("Dev", "Developer");
		list.put("TT", "Tester");
		list.put("QA", "Quality Assurence");
		return list;
	}

	@GetMapping("list")
	public String list() {
		return "students/list";
	}

	@GetMapping("search")
	public String search() {
		return "students/search";
	}

	@GetMapping("edit/{studentId}")
	public String edit(@PathVariable("studentId") String studentId, Model model) { // Sử dụng để lấy thông tin sau đường
																					// dẫn ex: studentId
		System.out.println("Edit StudentId: " + studentId);
		Student student = new Student();
		student.setStudentId(studentId);
		student.setStudentName("Son");
		model.addAttribute("student", student);
		return "students/edit";
	}

	@PostMapping("update")
	public String update(Student student, Model model) {

		model.addAttribute("studentId", student.getStudentId());
		model.addAttribute("studentName", student.getStudentName());
		return "students/viewDetail";
	}

}
