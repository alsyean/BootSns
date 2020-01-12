package com.sns.pjt.Service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Follow;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.FollowRepository;

@Service
public class FollowServiceImpl implements FollowService {

	private static final Logger logger = LoggerFactory.getLogger(FollowServiceImpl.class);
	
	@Autowired
	FollowRepository followRepository;

	@Autowired
	UserService userService;
	
	@Autowired
	FeedService feedService;

	public Follow Follow(Follow follow, HttpSession session) throws Exception {

		User follwee = userService.getUserById(follow.getFolloweeId());

		logger.info(follow.toString());
		logger.info("follwee" + follwee);
		
		Object follower = session.getAttribute("userId");
		
		Follow fol = null;
		

		try {

			fol = new Follow((int) follower, follwee.getId(), new Date());
		
			fol = followRepository.save(fol);
			
			feedService.insertFeed(session, follwee.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return fol;

	}

	@Override
	public int unFollow(Follow follow, HttpSession session) throws Exception {

		int del = 0;

		User follwee = userService.getUserById(follow.getFolloweeId());
		
		System.out.println("unUser : " + follow.toString());

		Object follower = session.getAttribute("userId");
		Follow deletefollow = null;

		try {

			deletefollow = new Follow((int) follower, follwee.getId(), new Date());

			del = followRepository.unFollow(follow.getFolloweeId());
			
			feedService.deleteFeed(session, follwee.getId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return del;

	}
	
	@Override
	public List<Follow> getFollowerAndFollowee(HttpSession session){
		
		Object userId = session.getAttribute("userId");
		
		List<Follow> follow = followRepository.getFollowerAndFollowee((int) userId);
		
		
		return follow;
	}

}
