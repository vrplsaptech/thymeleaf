package com.luv2code.springboot.thymeleafdemo.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BACKUP_DemoController {

	@GetMapping("/hello")
	public String sayHellow(Model theModel) {
		
		theModel.addAttribute("theDate", new java.util.Date());
		return "helloworld";
	}
}
