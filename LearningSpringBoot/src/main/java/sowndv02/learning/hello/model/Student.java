package sowndv02.learning.hello.model;

import java.util.List;

import javax.validation.constraints.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // tạo getter, setter
@AllArgsConstructor // Tạo constructor
@NoArgsConstructor
public class Student {
	// NotEmpty: Nó không rỗng và có kích thước > 0
	// NotBlank: có thể ràng buộc giá trị miền và khi bỏ space thì nó không rỗng
	@NotBlank
	private String studentId;
	@NotEmpty(message = "{NotEmpty.student.studentName}") // Lấy giá trị có key như kia để hiển thị
	@Min(value = 4)
	private String studentName;
	@Email
	@NotEmpty(message = "{NotEmpty.student.email}")
	@Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$", message = "")
	private String email;
	private String phoneNumber;
	private String dob;
	private String position;
	private List<String> hobbies;
	@Min(value = 0)
	@Max(value = 10)
	private double mark;
	private Country country;

	private MultipartFile imgFile;
	private String imgUrl;
}
