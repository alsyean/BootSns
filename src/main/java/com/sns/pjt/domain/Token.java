package com.sns.pjt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sns.pjt.common.util.Util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
public class Token {

	@Id
	private String token;

	@Column(name = "user_id")
	private int id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Column(insertable = false, updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;

	public Token() {
		this.token = Util.insertToken();
		this.createdAt = new Date();
	}

}
