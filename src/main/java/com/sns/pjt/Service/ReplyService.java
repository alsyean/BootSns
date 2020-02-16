package com.sns.pjt.Service;

import java.util.List;

import com.sns.pjt.domain.Reply;

public interface ReplyService {

	public Reply insertReply(Reply reply);
	
	public List<Reply> replyList(int postId);

	public boolean deleteReply(Reply reply);
}
