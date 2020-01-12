package com.sns.pjt.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.Controller.PostRestController;
import com.sns.pjt.Controller.dto.PostDto;
import com.sns.pjt.domain.Follow;
import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FollowService followService;

	User user = new User();

	@Override
	public Post insertPost(Post post, HttpSession session) {

		Object userId = session.getAttribute("userId");

		User user = userService.getUserById((int) userId);
		post.setCreatedAt(new Date());

		post.setUser(user);

		return postRepository.save(post);
	}

	@Override
	public List<Post> getFollowPost(HttpSession session) {

		Object userId = session.getAttribute("userId");

		List<Post> postList = postRepository.followPost((int) userId);
		List<Follow> follow = followService.getFollowerAndFollowee(session);

		if (follow.size() > 0) {

			Iterator<Post> postSet = postList.iterator();
			Iterator<Follow> followSet = follow.iterator();

			while (postSet.hasNext()) {

				Post post = postSet.next();
				Object uId = post.getUser().getId();

				for (int i = 0; i < follow.size(); i++) {

					// Follow follower = followSet.next();

					Object followerId = follow.get(i).getFollowerId();
					Object followeeId = follow.get(i).getFolloweeId();

					if (followerId.equals(userId) && followeeId.equals(uId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
						break;
					}else if (!uId.equals(userId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
						System.out.println("modify : " + post);
					}

				}
				
				if(post.getUser().getIsFollow() == null) {
					continue;
				}
				
				if(!post.getUser().getIsFollow()) {
					postSet.remove();
				}
			}

		} else if (follow.size() == 0) {

			Iterator<Post> postSet = postList.iterator();

			while (postSet.hasNext()) {

				Post post = postSet.next();

				Object uId = post.getUser().getId();

				if (!uId.equals(userId)) {
					postSet.remove();
				}

			}
		}

		logger.info("return postList : " + postList.toString());

		return postList;

	}

	@Override
	public List<Post> getPostList(HttpSession session) {

		Object userId = session.getAttribute("userId");

		List<Post> postList = null;

		if (userId == null) {
			
			postList = (List<Post>) postRepository.findAllByOrderByIdDesc();
			
		} else {

			postList = postRepository.followPost((int) userId);
			List<Follow> follow = followService.getFollowerAndFollowee(session);

			if (follow.size() > 0) {

				Iterator<Post> postSet = postList.iterator();
				Iterator<Follow> followSet = follow.iterator();

				while (postSet.hasNext()) {

					Post post = postSet.next();
					Object uId = post.getUser().getId();

					for (int i = 0; i < follow.size(); i++) {

						Object followerId = follow.get(i).getFollowerId();
						Object followeeId = follow.get(i).getFolloweeId();

						if (followerId.equals(userId) && followeeId.equals(uId)) {
							postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
							break;
						}else if (!uId.equals(userId)) {
							postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
						}

					}
				}

			} else if (follow.size() == 0) {

				Iterator<Post> postSet = postList.iterator();

				while (postSet.hasNext()) {

					Post post = postSet.next();

					Object uId = post.getUser().getId();

					if (!uId.equals(userId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
					}

				}
			}
		}

		return postList;
	}

	@Override
	public Post updatePost(Post post, HttpSession session) {

		Object userId = session.getAttribute("userId");

		User user = userService.getUserById((int) userId);

		Post updatePost = null;

		if (userId.equals(user.getId())) {

			updatePost = postRepository.findById(post.getId());

			updatePost.setTitle(post.getTitle());
			updatePost.setContent(post.getContent());

			postRepository.save(updatePost);
		}

		return updatePost;
	}

	@Override
	public Post detail(int postId) {

		Post detail = postRepository.findById(postId);

		return detail;
	}

	@Override
	public int deletePost(int postId, HttpSession session) throws Exception {
		int del = 0;

		Object userId = session.getAttribute("userId");

		User user = userService.getUserById((int) userId);

		try {

			if (userId.equals(user.getId())) {
				Post post = postRepository.findById(postId);

				del = postRepository.deletePost(post.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return del;
	}

}
