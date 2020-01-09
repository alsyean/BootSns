package com.sns.pjt.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sns.pjt.Service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
}
