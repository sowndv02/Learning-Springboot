package sowndv02.learning.sample.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	private String studentId;
	private String name;
	private boolean gender;
	private float gpa;
	private Date dob;
}
