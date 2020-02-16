package com.sns.pjt.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.pjt.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply,Integer>{

	List<Reply> findByPostId(int postId);
	
	Reply findByPostIdAndUserAndReplyPassword(int postId, String user, String replyPassword);
	
}
