
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
import com.sns.pjt.domain.Post;

@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Autowired
	private PostService postService;

	@GetMapping(value = "/post/detail/{postId}")
	public String detail(@PathVariable int postId, Model model, HttpSession session) {

		Object sessionCheck = session.getAttribute("userId");
		
		Post postWrite = postService.writeCheck(postId);
		
		if(sessionCheck != null) {
			if(postWrite.getUser().getId() == (int) sessionCheck) {
				model.addAttribute("write", true);
			}else {
				model.addAttribute("write", false);
			}
		}else {
			model.addAttribute("write", false);
		}
		
		model.addAttribute("id", postId);
		model.addAttribute("viewCount", postWrite.getViews() + 1);

		return "detail";
	}
}
