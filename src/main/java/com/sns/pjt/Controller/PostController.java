
package com.sns.pjt.Controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sns.pjt.Service.PostService;
import com.sns.pjt.Service.RedisPostService;

@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@Autowired
	private RedisPostService redisPostService;

	@GetMapping(value = "/post/detail/{postId}")
	public String detail(@PathVariable int postId, Model model, HttpSession session) {

		model.addAttribute("id", postId);
		model.addAttribute("viewCount", redisPostService.viewCount(postId));

		return "detail";
	}
}
