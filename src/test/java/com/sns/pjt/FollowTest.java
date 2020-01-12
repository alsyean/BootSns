package com.sns.pjt;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sns.pjt.Controller.dto.PostDto;
import com.sns.pjt.Service.UserService;
import com.sns.pjt.domain.Follow;
import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.FollowRepository;
import com.sns.pjt.persistence.PostRepository;
import com.sns.pjt.persistence.TokenRepository;
import com.sns.pjt.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FollowTest {

	@Autowired
	private FollowRepository followRepository;

	@Autowired
	private UserService userService;

	@Test
	public void testInsertFollow() {

		User user = userService.getUserById(1);

		System.out.println(user.toString());
		/*
		 * Follow follow = new Follow(); follow.setFollowerId(user.getId());
		 * follow.setFolloweeId(2); follow.setCreatedAt(new Date());
		 */

		Follow follow = new Follow(user.getId(), 3, new Date());

		System.out.println(follow.toString());

		followRepository.save(follow);

	}
	
	@Test
	@Ignore
	public void testDeleteFollow() {
		
		User user = userService.getUserById(1);

		System.out.println(user.toString());
		/*
		 * Follow follow = new Follow(); follow.setFollowerId(user.getId());
		 * follow.setFolloweeId(2); follow.setCreatedAt(new Date());
		 */

		Follow follow = new Follow(user.getId(), 2, new Date());

		System.out.println(follow.toString());

		followRepository.unFollow(follow.getFolloweeId());
		
	}
	
	@Test
	@Ignore
	public void testGetFollower() {
		
		List<Follow> follow = followRepository.getFollowerAndFollowee(1);
		
		System.out.println(follow);
		
	}

}
