package sowndv02.learning.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller phải là package con của package chứa class Main. Nếu không thì phải khai báo

@Controller //Khai báo controller
public class HelloController {
	
	@RequestMapping("index") //Truy cập tới action này thông qua URL gốc ("") còn đây là index
	@ResponseBody //Nhận trực tiếp nội dung trả về ở body là hello
	public String hello() {
		return "hello"; //Tên view được tìm kiếm trong views và hậu tố là jsp (Đã khai báo ở application.properties)
	}

}
