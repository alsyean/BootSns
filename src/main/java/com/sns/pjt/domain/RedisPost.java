//package com.sns.pjt.domain;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//
//import org.springframework.data.redis.core.RedisHash;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@RedisHash("post")
//@Getter
//@Setter
//@ToString
//public class RedisPost {
//
//	@Id
//	@Column(name = "\"id\"")
//	private int id;
//	private String title;
//	private String content;
//
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
//	@Temporal(value = TemporalType.TIMESTAMP)
//	@Column(updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
//	private Date createdAt;
//
//	@Transient
//	private int viewCnt;
//
//}
