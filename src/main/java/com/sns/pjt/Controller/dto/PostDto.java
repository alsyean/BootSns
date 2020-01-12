package com.sns.pjt.Controller.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
public class PostDto {

	/*
	 * private List<Post> post;
	 * 
	 * private int id; private String username; private Date createdAt;
	 */
	
	private Post post;
	private User user;
	List<Object> dto; // = new ArrayList<Object>();
	
	public PostDto(String post , String user, List<Object> postdto) {
		this.dto = postdto;
		postdto.add(post);
		postdto.add(user);
	}

}
