package com.sns.pjt.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Reply;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.ReplyRepository;

@Service
public class ReplyServiceImpl implements ReplyService{

	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reply insertReply(Reply reply) {
		// TODO Auto-generated method stub
		
		reply.setCreatedAt(new Date());
		Reply insertReply = replyRepository.save(reply);
		
		return insertReply;
	}

	@Override
	public List<Reply> replyList(int postId){
		
		List<Reply> replyList = replyRepository.findByPostId(postId);
		
		return replyList;
		
	}

	@Override
	public boolean deleteReply(Reply reply) {
		Reply delteReply = replyRepository.findByPostIdAndUserAndReplyPassword(reply.getPostId(), reply.getUser(), reply.getReplyPassword());
		
		boolean delete = false;
		
		replyRepository.deleteById(delteReply.getId());
		
		if(delteReply == null) {
			delete = true;
		}
		
		
		return delete; 	
	}


}
