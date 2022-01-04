package aloha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	
	@RequestMapping("/")
	public @ResponseBody String main() throws Exception {
	
		return "Spring Security test Page";
	}
	
	
	@RequestMapping("/guest/welcome")
	public String guestWelcome() {
		return "guest/welcome";
	}
	
	@RequestMapping("/member/welcome")
	public String memeberWelcome() {
		return "member/welcome";
	}
	
	@RequestMapping("/admin/welcome")
	public String adminWelcome() {
		return "admin/welcome";
	}
	

}
