package com.sns.pjt.Service;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Feed;

public interface FeedService {
	
	public void insertFeed(HttpSession session, int followee);
	
	public void deleteFeed(HttpSession session, int followee);

}
