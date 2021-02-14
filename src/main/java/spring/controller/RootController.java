package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

	@GetMapping("/")
	public String root() {
		return "forward:top";
	}

	@GetMapping("/top")
	public String top() {
		return "recog";
	}
}
