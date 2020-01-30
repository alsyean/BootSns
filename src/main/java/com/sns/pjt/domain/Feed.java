package com.sns.pjt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(FeedPK.class)
public class Feed {

	@Id
	private int userId;
	@Id
	private int followeeId;
	@Id
	private int postId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;

	public Feed(int userId, int followeeId, int postId) {
		this.userId = userId;
		this.followeeId = followeeId;
		this.postId = postId;
	}
	
	public Feed() {
		
	}

}
