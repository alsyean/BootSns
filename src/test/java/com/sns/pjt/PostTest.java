package com.sns.pjt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.mock.web.MockHttpSession;
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
public class PostTest {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private FollowRepository followRepository;

	User user = new User();

	User user1 = new User();

	/*
	 * @Before public void testInserUser() {
	 * 
	 * user.setUsername("yy"); user.setPassword("yy");
	 * 
	 * userRepository.save(user);
	 * 
	 * user1.setUsername("aa"); user1.setPassword("aa");
	 * 
	 * userRepository.save(user1);
	 * 
	 * Post post1 = new Post(); post1.setTitle("testTitle1");
	 * post1.setContent("testContent1"); post1.setCreatedAt(new Date());
	 * post1.setUser(user);
	 * 
	 * postRepository.save(post1);
	 * 
	 * Post post2 = new Post(); post2.setTitle("testTitle2");
	 * post2.setContent("testContent2"); post2.setCreatedAt(new Date());
	 * post2.setUser(user1); postRepository.save(post2);
	 * 
	 * Post post3 = new Post(); post3.setTitle("testTitle23");
	 * post3.setContent("testContent23"); post3.setCreatedAt(new Date());
	 * 
	 * post3.setUser(user1); postRepository.save(post3);
	 * 
	 * }
	 */

	@Test
	@Ignore
	public void testInsertPost() {

		Post post = new Post();

		System.out.println("user : " + user.toString());

		post.setTitle("testTitle");
		post.setContent("testContent");
		post.setCreatedAt(new Date());
		// post.setUserId(user.getId());
		post.setUser(user);

		postRepository.save(post);

		List<Post> postList = user.getPostList();

		for (Post list : postList) {
			System.out.println(list.toString());
			System.out.println(list.getUser().getId());
		}

	}

	@Test
	public void testPostList() {

		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "id");

		List<Post> postList = (List<Post>) postRepository.findAllByOrderByIdDesc(paging);

		Pageable nextPage = PageRequest.of(1, 5, Sort.Direction.DESC, "id");

		List<Post> nextPostList = postRepository.findAllByOrderByIdDesc(nextPage);

		

		Iterator<Post> postIterator = postList.iterator();

		if (nextPostList.isEmpty()) {
			while (postIterator.hasNext()) {

				Post post = postIterator.next();
				postList.set(postList.indexOf(post), post).setPageCheck(true);
				System.out.println("post : " + postList.toString());
			}
		}
		for (Post list : postList) {

			System.out.println("list : " + list.toString());
		}

	}

	@Test
	@Ignore
	public void testFeedPostList() {

		System.out.println("----------");

		List<Post> post = postRepository.findByUserIdOrderByIdDesc(2);

		for (Post list : post) {

			System.out.println("list : " + list.toString());

		}

		System.out.println("PostList : " + post.toString());

	}

	@Test
	@Ignore
	public void testUpdate() {

		Post post = postRepository.findById(1);

		post.setTitle("22222222");
		post.setContent("333333");

		postRepository.save(post);

		System.out.println(post);

	}

	@Test
	@Ignore
	public void testdelete() {

		Post post = postRepository.findById(2);

		System.out.println("post : " + post);

		System.out.println("Id : " + post.getId());

		postRepository.deletePost(post.getId());

		System.out.println("--------");

	}

	/*
	 * @Before public void Session(HttpSession session) {
	 * 
	 * User user = new User(); user.setId(1); user.setUsername("yy");
	 * user.setPassword("yy");
	 * 
	 * session = new MockHttpSession(); session.setAttribute("userId",
	 * user.getId());
	 * 
	 * }
	 */

	@Test
	@Ignore
	public void testFeedPost() {

		User users = userService.getUserById(1);

		List<Post> postList = postRepository.followPost(users.getId());

		List<Follow> follow = followRepository.getFollowerAndFollowee(users.getId());

		int lastIndex = 0;

		System.out.println("follow size : " + follow);
		System.out.println("post size : " + postList.size());
		System.out.println("user : " + users);

		if (follow.size() > 0) {

			Iterator<Post> postIterator = postList.iterator();
			Iterator<Follow> followIterator = follow.iterator();

			while (postIterator.hasNext()) {

				Post post = postIterator.next();

				Object uId = post.getUser().getId();

				System.out.println("postListIndex : " + postList.indexOf(post));

//				while (followIterator.hasNext()) {
				for (int i = 0; i < follow.size(); i++) {
//					Follow follower = followIterator.next();

//					Object followerId = follow.get(follow.indexOf(follower)).getFollowerId();
//					Object followeeId = follow.get(follow.indexOf(follower)).getFolloweeId();

					Object followerId = follow.get(i).getFollowerId();
					Object followeeId = follow.get(i).getFolloweeId();

					System.out.println("followerId : " + followerId);
					System.out.println("followeeId : " + followeeId);
					System.out.println("uId : " + uId);
					System.out.println("users : " + users.getId());

					lastIndex = post.getId();

					if (followerId.equals(users.getId()) && followeeId.equals(uId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
						break;
					} else if (!uId.equals(users.getId())) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
						System.out.println("modify : " + post);
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

				if (!uId.equals(users.getId())) {
					System.out.println("delete : " + post);
					postIterator.remove();
				}

			}
		}

		List<Post> followPost = new ArrayList<Post>();

		System.out.println("#######################");
		System.out.println(postList.toString());

		int fisrtPage = 4;
		int pageSize = 6;

		if (postList.size() < pageSize) {
			pageSize = postList.size();

		}

		for (; fisrtPage < pageSize; fisrtPage++) {
			Post post = postList.get(fisrtPage);
			followPost.add(post);
		}

		System.out.println("@!!!!!!!!!@@");
		System.out.println(followPost.toString());

		Iterator<Post> followIterator = followPost.iterator();

		if (postList.size() <= pageSize) {
			while (followIterator.hasNext()) {

				Post fpost = followIterator.next();

				followPost.set(followPost.indexOf(fpost), fpost).setPageCheck(true);
			}
		}

		System.out.println("@@@@@@@@@@@@@@@@@");
		System.out.println(followPost.toString());

	}

	@Test
	@Ignore
	public void testAllFeedPostList() {

		User users = userService.getUserById(1);

		Pageable paging = PageRequest.of(0, 5, Sort.Direction.DESC, "id");

		List<Post> postList = postRepository.allPost(users.getId(), paging);

		List<Follow> follow = followRepository.getFollowerAndFollowee(users.getId());

		Pageable nextPage = PageRequest.of(1, 5, Sort.Direction.DESC, "id");

		List<Post> nextPostList = postRepository.allPost(users.getId(), nextPage);

		System.out.println("follow size : " + follow);
		System.out.println("post size : " + postList.size());
		System.out.println("user : " + users);

		if (follow.size() > 0) {

			Iterator<Post> postIterator = postList.iterator();
			Iterator<Follow> followIterator = follow.iterator();

			while (postIterator.hasNext()) {

				Post post = postIterator.next();

				Object uId = post.getUser().getId();

				System.out.println("postListIndex : " + postList.indexOf(post));

				for (int i = 0; i < follow.size(); i++) {

					Object followerId = follow.get(i).getFollowerId();
					Object followeeId = follow.get(i).getFolloweeId();

					System.out.println("followerId : " + followerId);
					System.out.println("uId : " + uId);
					System.out.println();
					System.out.println("followeeId : " + followeeId);
					System.out.println("fId : " + users.getId());
					System.out.println();

					if (paging.next().getPageNumber() > paging.getPageSize() || nextPostList.isEmpty()) {
						System.out.println("#size#");
						postList.set(postList.indexOf(post), post).setPageCheck(true);
					}

					if (followerId.equals(users.getId()) && followeeId.equals(uId)) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(true);
						break;
					} else if (!uId.equals(users.getId())) {
						postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
						System.out.println("modify : " + post);
					}

				}
			}

		} else if (follow.size() == 0) {

			Iterator<Post> postIterator = postList.iterator();

			while (postIterator.hasNext()) {

				Post post = postIterator.next();

				Object uId = post.getUser().getId();

				if (paging.next().getPageNumber() > paging.getPageSize() || nextPostList.isEmpty()) {
					System.out.println("#size#");
					postList.set(postList.indexOf(post), post).setPageCheck(true);
				}

				if (!uId.equals(users.getId())) {
					postList.set(postList.indexOf(post), post).getUser().setIsFollow(false);
				}

			}
		}

		System.out.println(paging.hasPrevious());
		System.out.println(paging.getPageSize());
		System.out.println(paging.next().getPageNumber());
		System.out.println(paging.getPageNumber());
		System.out.println(paging.first());
		System.out.println(paging.previousOrFirst());

		System.out.println("#######################");
		System.out.println(postList.toString());

	}

}
