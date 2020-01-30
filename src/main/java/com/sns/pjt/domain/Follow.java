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


@Setter
@Getter
@ToString
@Entity
@IdClass(FollowPK.class)
public class Follow {

	@Id
	private int followerId;
	@Id
	private int followeeId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;
	
	public Follow(int followerId,int followeeId,Date createdAt) {
		this.followerId = followerId;
		this.followeeId = followeeId;
		this.createdAt = createdAt;		
	}
	
	public Follow() {
		
	}
}
