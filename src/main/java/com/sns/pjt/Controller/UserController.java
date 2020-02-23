package com.sns.pjt.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.pjt.Service.PostService;
import com.sns.pjt.Service.UserService;
import com.sns.pjt.domain.Post;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;


	@Autowired
	private PostService postService;
	
	//메인 페이지
	@GetMapping(value = "/")
	public String index(Model model, HttpSession session) {

		Object sessionCheck = session.getAttribute("userId");

		if (sessionCheck != null) {

			logger.info("로그인한 아이디 : " + sessionCheck.toString());

			model.addAttribute("userId", sessionCheck);
		}
		
		return "index";
	}

	// 회원 가입 페이지
	@GetMapping(value = "/signup")
	public String signup(Model model) {
		return "signup";
	}

	// 로그인 페이지
	@GetMapping(value = "/login")
	public String Login() {
		return "login";
	}

	// 로그 아웃
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {

		Object sessionCheck = session.getAttribute("userId");

		if (sessionCheck != null) {

			session.removeAttribute("userId");

		}

		return "login";
	}
	
	//이메일 인증
	@GetMapping(value = "/verify")
	public String signSuccess(@RequestParam String email, @RequestParam String username) {
		logger.info("이메일 인증 기능 처리");
	
		String page = "redirect:login";
		
		Boolean getAdmin = userService.insertIsAdmin(email, username);
	
		if(getAdmin == null) {
			page = "redirect:signup";
		}
		
		return page;
	}
}
