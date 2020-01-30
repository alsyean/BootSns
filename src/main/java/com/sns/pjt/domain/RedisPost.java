package com.sns.pjt.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@RedisHash("post")
@Getter
@Setter
@ToString
public class RedisPost {
	@Id
	private int id;

	@Transient
	private int viewCnt;

}
