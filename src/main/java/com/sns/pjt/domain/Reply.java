package com.sns.pjt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "\"id\"")
	private int id;
	private int postId;
	private String user;
	private String comment;
	private String replyPassword;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column( updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;
	
}
