package com.sns.pjt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Entity
public class Post{ // implements Comparable<Post> , Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"id\"")
	private int id;
	private String title;
	private String content;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column( updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;
	
	@Transient
	private boolean pageCheck = false; 
	
	@Column
	private int views ;

	@ManyToOne
	// @JsonBackReference
	@JsonProperty
	private User user;

	public void setUser(User user) {
		this.user = user;
		user.getPostList().add(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

//	@Override
//	public int compareTo(Post o) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
