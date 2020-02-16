
package com.sns.pjt.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.PostRepository;

@Service
public interface PostService {

	public Post insertPost(Post post, HttpSession session);

	public List<Post> getPostList(HttpSession session, int AllPostPage);

	public List<Post> getFollowPost(HttpSession session, int feedPage);

	public Post updatePost(Post post, HttpSession session);

	public Post detail(int postId);

	public int deletePost(int postId, HttpSession session) throws Exception;
	
	public Post writeCheck(int postId);
}
