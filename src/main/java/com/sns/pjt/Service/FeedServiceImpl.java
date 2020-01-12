package com.sns.pjt.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Feed;
import com.sns.pjt.domain.Post;
import com.sns.pjt.persistence.FeedRepository;
import com.sns.pjt.persistence.PostRepository;

@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private FeedRepository feedRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void insertFeed(HttpSession session, int followee) {

		Object userId = session.getAttribute("userId");

		List<Post> post = postRepository.findByUserIdOrderByIdDesc(followee);

		for (Post list : post) {

			Feed feed = new Feed((int) userId, followee, list.getId());

			feedRepository.save(feed);

		}

	}

	@Override
	public void deleteFeed(HttpSession session, int followee) {

		Object userId = session.getAttribute("userId");

		List<Post> post = postRepository.findByUserIdOrderByIdDesc(followee);

		for (Post list : post) {

			Feed feed = new Feed((int) userId, followee, list.getId());

			feedRepository.deleteFeed(followee);

		}

	}

}
