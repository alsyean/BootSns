package com.sns.pjt.Controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.pjt.Controller.dto.ResultDto;
import com.sns.pjt.Service.UserService;
import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;

@RestController
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	@Autowired
	private UserService userService;
	

	// 회원가입 기능
	@PostMapping(value = "/user", produces = "application/json; charset=utf-8")
	public ResultDto insertUser(@RequestBody User user) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);
		try {
			user.setCreatedAt(new Date());
			User result = userService.insertUser(user);

			if (result != null) {
						
				Boolean sendEmail = userService.insertEmail(user);
				
				if(sendEmail) {
					resultDto = new ResultDto(200, "Success", result);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	} 

	// 로그인 기능
	@PostMapping(value = "/auth", produces = "application/json; charset=utf-8")
	public ResultDto userLogin(@RequestBody User user, HttpSession session) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);
		
		logger.info("User : " + user.toString());

		try {
			
			Boolean role = userService.roleCheck(user);
			
			if(role) {
				User result = userService.getUserInfoByUsernameAndPassword(user);
				
				if (result.getUsername().equals(user.getUsername()) && result.getPassword().equals(user.getPassword())) {
					
					Token token = userService.insertToken(result,session);
					
					logger.info("token : " + token.toString());
					
					resultDto = new ResultDto(200, "Success", token);
				}	
			}else if(!role){
				resultDto = new ResultDto(403, "Fail", null);
			}else {
				resultDto = new ResultDto(400, "Fail", null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info(resultDto.toString());
		
		return resultDto;
	}

	// 쿠키를 넣기 위한 유저 찾기
	@GetMapping(value = "/user")
	public ResultDto userFind(@RequestParam("id") String id, User user, Model model) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {
			User result = userService.getUserById(user.getId());

			if (result != null) {

				resultDto = new ResultDto(200, "Success", result);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;

	}
	
	//유저 이름 중복 체크
	@PostMapping(value = "/user/overlap", produces = "application/json; charset=utf-8")
	public ResultDto overlapUserName(@RequestBody User dto) {		
		
		logger.info("overLap");
		
		ResultDto result = new ResultDto(200,"Success", null);
		
		Boolean overlap = userService.overlapUserName(dto);
		
		if(overlap) {
			result = new ResultDto(200,"Success", overlap);
		}else if(!overlap) {
			result = new ResultDto(200,"Success", overlap);
		}
		
		System.out.println(result);
		
		return result;
	}

}
