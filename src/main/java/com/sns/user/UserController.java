package com.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
	

		@RequestMapping("/sign_up_view")
		public String signUpView(Model model) {
			model.addAttribute("viewName", "user/sign_up"); // user 레이아웃 변경 
			return"template/layout";
		}
		
		
		
		
		@RequestMapping("/sign_in_view")
		public String signInView(Model model) {
			model.addAttribute("viewName", "user/sign_in"); // user 레이아웃 변경 
			return"template/layout";
		}
		
		@RequestMapping("/sign_out")
		public String signOut(
				HttpServletRequest request) {
			
			HttpSession session = request.getSession();
			session.removeAttribute("userName");
			session.removeAttribute("userId");
			session.removeAttribute("userLoginId");
			
			return "redirect:/timeline/timeline_list_view";
		}
	}
