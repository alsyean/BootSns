
package com.sns.pjt.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
	public Post insertPost(Post post, HttpSession session) {

		Object userId = session.getAttribute("userId");

		User user = userService.getUserById((int) userId);
		post.setCreatedAt(new Date());

		post.setUser(user);

		return postRepository.save(post);
	}

	@Override
	public List<Post> getFollowPost(HttpSession session, int feedPage) {

		Object userId = session.getAttribute("userId");

		int pageSize = 5;

		List<Post> postList = postRepository.followPost((int) userId);
		List<Follow> follow = followService.getFollowerAndFollowee(session);

		if (follow.size() > 0) {

			Iterator<Post> postIterator = postList.iterator();

			while (postIterator.hasNext()) {

				Post post = postIterator.next();
				Object uId = post.getUser().getId();

				for (int i = 0; i < follow.size(); i++) {

					// Follow follower = followIterator.next();

					Object followerId = follow.get(i).getFollowerId();
					Object followeeId = follow.get(i).getFolloweeId();

					if (followerId.equals(userId) && followeeId.equals(uId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
						break;
					} else if (!uId.equals(userId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
					}

				}

				if (post.getUser().getIsFollow() == null) {
					continue;
				}

				if (!post.getUser().getIsFollow()) {
					postIterator.remove();
				}
			}

		} else if (follow.size() == 0) {

			Iterator<Post> postIterator = postList.iterator();

			while (postIterator.hasNext()) {

				Post post = postIterator.next();

				Object uId = post.getUser().getId();

				if (!uId.equals(userId)) {
					postIterator.remove();
				}

			}
		}

		List<Post> followPost = new ArrayList<Post>();

		int fisrtPage = feedPage;
		int lastPage = feedPage + pageSize;

		System.out.println("");

		if (postList.size() < lastPage) {
			lastPage = postList.size();

		}

		for (; fisrtPage < lastPage; fisrtPage++) {
			Post post = postList.get(fisrtPage);
			followPost.add(post);
		}

		Iterator<Post> followIterator = followPost.iterator();

		logger.info("fisrtPage :" + fisrtPage + ", pageSize : " + lastPage);

		if (postList.size() <= lastPage || postList.size() == follow.size()) {
			while (followIterator.hasNext()) {

				Post fpost = followIterator.next();

				followPost.set(followPost.indexOf(fpost), fpost).setPageCheck(true);
			}
		}

		logger.info("return postList : " + followIterator.toString());

		return followPost;

	}

	@Override
	public List<Post> getPostList(HttpSession session, int AllPostPage) {

		Object userId = session.getAttribute("userId");

		int pageSize = 5;

		List<Post> postList = null;

		List<Post> nextPostList = null;

		Pageable paging = PageRequest.of(AllPostPage, pageSize, Sort.Direction.DESC, "id");

		Pageable nextPage = PageRequest.of(AllPostPage + 1, pageSize, Sort.Direction.DESC, "id");

		if (userId == null) {

			postList = postRepository.findAllByOrderByIdDesc(paging);

			nextPostList = postRepository.findAllByOrderByIdDesc(nextPage);

			Iterator<Post> postIterator = postList.iterator();

			if (nextPostList.isEmpty()) {
				while (postIterator.hasNext()) {

					Post post = postIterator.next();
					postList.set(postList.indexOf(post), post).setPageCheck(true);

				}
			}

		} else {

			nextPostList = postRepository.allPost((int) userId, nextPage);

			postList = postRepository.allPost((int) userId, paging);

			List<Follow> follow = followService.getFollowerAndFollowee(session);

			if (follow.size() > 0) {
				Iterator<Post> postIterator = postList.iterator();

				while (postIterator.hasNext()) {

					Post post = postIterator.next();
					Object uId = post.getUser().getId();

					for (int i = 0; i < follow.size(); i++) {

						Object followerId = follow.get(i).getFollowerId();
						Object followeeId = follow.get(i).getFolloweeId();

						if (followerId.equals(userId) && followeeId.equals(uId)) {
							postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
							break;
						} else if (!uId.equals(userId)) {
							postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
						}

					}

					if (nextPostList.isEmpty()) {
						postList.set(postList.indexOf(post), post).setPageCheck(true);
					}

				}

			} else if (follow.size() == 0) {
				Iterator<Post> postIterator = postList.iterator();

				while (postIterator.hasNext()) {

					Post post = postIterator.next();

					Object uId = post.getUser().getId();

					if (!uId.equals(userId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
					}

					if (nextPostList.isEmpty()) {
						postList.set(postList.indexOf(post), post).setPageCheck(true);
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
			updatePost.setCreatedAt(new Date());

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
