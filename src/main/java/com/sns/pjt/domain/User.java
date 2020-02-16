package com.sns.pjt.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.sns.pjt.util.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "postList")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = false, updatable = false)
	private int id;

	private String username;
	private String password;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "Asia/Seoul")
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column( updatable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	private Date createdAt;
	 
	private String email;
	
	private Role isAdmin;
	
	@Transient
	private Boolean isFollow;

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}

	// @JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Post> postList = new ArrayList<Post>();

	public void setIsFollow(Boolean isFollow) {
		this.isFollow = isFollow;
	}

	
	
}
