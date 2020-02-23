package com.sns.pjt.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.pjt.Controller.dto.ResultDto;
import com.sns.pjt.Service.FollowService;
import com.sns.pjt.domain.Follow;
import com.sns.pjt.domain.User;

@RestController
public class FollowRestController {
	private static final Logger logger = LoggerFactory.getLogger(FollowRestController.class);

	@Autowired
	private FollowService followService;

	//팔로우
	@PostMapping(value = "/follow", produces = "application/json; charset=utf-8")
	public ResultDto follow(@RequestBody Follow follow, HttpSession session) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);
		
		try {

			Follow follower = followService.Follow(follow, session);

			if (follow != null) {

				resultDto = new ResultDto(200, "OK", "Success");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}
	
	//팔로우 해체
	@DeleteMapping(value = "/follow", produces = "application/json; charset=utf-8")
	public ResultDto unFollow(@RequestBody Follow follow, HttpSession session) throws Exception {

		System.out.println("###################");

		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {

			int del = followService.unFollow(follow, session);

			if (del > 0) {

				resultDto = new ResultDto(200, "OK", "Success");

				logger.info("delete follow");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}
}
