package com.sns.pjt.domain;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "reply")
public class MongoReply implements Serializable{

	@Id
	private String id;
	
	private int postId;
	
	private String replyContent;
	
	
	
}
