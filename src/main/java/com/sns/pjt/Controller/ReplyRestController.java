package com.sns.pjt.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.pjt.Controller.dto.ResultDto;
import com.sns.pjt.Service.ReplyService;
import com.sns.pjt.domain.Reply;

@RestController
public class ReplyRestController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyRestController.class);
	
	@Autowired
	private ReplyService replyService;
	
	@PostMapping(value ="/comment", produces = "application/json; charset=utf-8")
	public ResultDto insertReply(@RequestBody Reply reply) throws Exception {
		
		
		ResultDto resultDto = new ResultDto(400, "Fail", null);
		
		try {
			Reply insertReply = replyService.insertReply(reply);
			
			resultDto = new ResultDto(200, "Success", insertReply);	
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		return resultDto;
	}
	
	@GetMapping(value = "/comments")
	public ResultDto getPostList(@RequestParam(value = "post_id") int postId) throws Exception {
		
		ResultDto resultDto = new ResultDto(400, "Fail", null);

		try {
			
			List<Reply> replyList = replyService.replyList(postId);
			
			resultDto = new ResultDto(200, "Success", replyList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultDto;
	}

	
	@PostMapping(value ="/comment/delete", produces = "application/json; charset=utf-8")
	public ResultDto deleteReply(@RequestBody Reply reply) throws Exception {
		
		ResultDto resultDto = new ResultDto(400, "Fail", null);
		
		try {
			boolean delete = replyService.deleteReply(reply);
			
			resultDto = new ResultDto(200, "Success", delete);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		
		return resultDto;
	}
}
