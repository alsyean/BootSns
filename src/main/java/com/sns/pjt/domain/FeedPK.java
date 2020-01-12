package com.sns.pjt.domain;

import java.io.Serializable;

import javax.persistence.Id;

public class FeedPK implements Serializable {
	

	private int userId;
	private int followeeId;
	private int postId;
	
}
