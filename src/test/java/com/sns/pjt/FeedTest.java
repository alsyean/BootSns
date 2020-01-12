package com.sns.pjt;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sns.pjt.Service.PostService;
import com.sns.pjt.Service.UserService;
import com.sns.pjt.domain.Feed;
import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.FeedRepository;
import com.sns.pjt.persistence.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedTest {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private PostRepository postRepository;

	@Test
	@Ignore
	public void testInsertFeed() {

		System.out.println("---------> testInsertFeed");

		// User user = userService.getUserById(1);
		List<Post> post = postRepository.findByUserIdOrderByIdDesc(1);

		for (Post list : post) {

			Feed feed = new Feed(1, list.getUser().getId(), list.getId());
//			Feed feed = new Feed((int) userId, followee, list.getId());
			feedRepository.save(feed);

		}

		System.out.println("feed : " + post.toString());
	}

	@Test
	public void testDelete() {

		System.out.println("---------> testDelete");

		List<Post> post = postRepository.findByUserIdOrderByIdDesc(3);
		
		System.out.println("post : " + post);

		for (Post list : post) {

			Feed feed = new Feed(1, list.getUser().getId(), list.getId());

			feedRepository.deleteFeed(feed.getFolloweeId());

		}

	}

}
