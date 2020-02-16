package com.sns.pjt.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sns.pjt.domain.MongoReply;

public interface ReplyRepository extends MongoRepository<MongoReply,String>{

	
	List<MongoReply> findByPostId(int postId);
}
