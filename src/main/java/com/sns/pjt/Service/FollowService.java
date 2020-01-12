package com.sns.pjt.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.repository.query.Param;

import com.sns.pjt.domain.Follow;
import com.sns.pjt.domain.User;

public interface FollowService {

	
	public Follow Follow(Follow follow, HttpSession session) throws Exception; 
	
	
	public int unFollow(Follow follow, HttpSession session) throws Exception;
	
	public List<Follow> getFollowerAndFollowee(HttpSession session);
}
