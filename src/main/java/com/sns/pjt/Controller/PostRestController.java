
package com.sns.pjt.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sns.pjt.Controller.dto.ResultDto;
import com.sns.pjt.Service.PostService;
import com.sns.pjt.domain.Post;

import lombok.Delegate;

@RestController
public class PostRestController {

	private static final Logger logger = LoggerFactory.getLogger(PostRestController.class);

	@Autowired
	private PostService postService;

	@PostMapping(value = "/post", produces = "application/json; charset=utf-8")
	public ResultDto insertPost(@RequestBody Post post, HttpSession session) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {

			postService.insertPost(post, session);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	@GetMapping(value = "/postAll/{AllPostPage}", produces = "application/json; charset=utf-8")
	public ResultDto getPostList(@PathVariable("AllPostPage") int AllPostPage, HttpSession session) throws Exception {

		logger.info("Allpage : " + AllPostPage);
		
		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {

			List<Post> postList = postService.getPostList(session, AllPostPage);

			resultDto = new ResultDto(200, "Succeess", postList);
			
			
			logger.info("postList : " + postList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	@GetMapping(value = "/post/feed/{feedPage}", produces = "application/json; charset=utf-8")
	public ResultDto getPostFeedList(@PathVariable("feedPage")int feedPage,HttpSession session) throws Exception {

		logger.info("feedpage : " + feedPage);
		
		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {

			List<Post> postList = postService.getFollowPost(session,feedPage);

			resultDto = new ResultDto(200, "Succeess", postList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	@GetMapping(value = "/post/{postId}")
	public ResultDto detailPost(@PathVariable("postId") int postId) throws Exception {

		ResultDto resultDto = new ResultDto(500, "Fail", null);

		try {

			Post detail = postService.detail(postId);

			if (detail != null) {

				resultDto = new ResultDto(200, "Succeess", detail);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	@PutMapping(value = "/post", produces = "application/json; charset=utf-8")
	public ResultDto updatePost(@RequestBody Post post, HttpSession session) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {
			Post update = postService.updatePost(post, session);

			if (update != null) {
				resultDto = new ResultDto(200, "Succeess", update);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	@DeleteMapping(value = "/post/{postId}", produces = "application/json; charset=utf-8")
	public ResultDto deletePost(@PathVariable("postId") int postId, HttpSession session) throws Exception {

		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {

			int del = postService.deletePost(postId, session);
			
			if (del > 0) {
				resultDto = new ResultDto(200, "Success", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

}
