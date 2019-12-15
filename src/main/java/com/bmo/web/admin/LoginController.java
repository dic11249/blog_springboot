package com.bmo.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bmo.po.User;
import com.bmo.service.UserService;
import com.bmo.util.MD5Utils;

@Controller
@RequestMapping("/admin")
public class LoginController {

	@Autowired UserService userSvc;
	
	
	@GetMapping("/login")
	public String loginPage() {
		return "admin/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String username,
						@RequestParam String password,
						HttpSession session,
						RedirectAttributes attributes) {

		User user = userSvc.checkUser(username, MD5Utils.code(password));
		if(user != null) {
			user.setPassword(null);
			session.setAttribute("user", user);
			return "redirect:/admin/index";
		} else {
			attributes.addFlashAttribute("message", "帳號或密碼錯誤");
			return "redirect:/admin";
		}
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/admin";
	}
}
