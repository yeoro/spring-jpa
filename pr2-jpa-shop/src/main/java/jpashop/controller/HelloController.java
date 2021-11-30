package jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping("hello")
	public String Hello(Model model) {
		
		// model : 데이터를 실어서 view에 넘길 수 있음
		model.addAttribute("data", "hello!");
		return "hello"; // 문자열이 아닌 화면 이름
		
	}
	
}
