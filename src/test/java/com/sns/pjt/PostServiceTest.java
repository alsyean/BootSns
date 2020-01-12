package com.sns.pjt;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sns.pjt.Controller.dto.ResultDto;
import com.sns.pjt.Service.PostService;
import com.sns.pjt.domain.Post;
import com.sns.pjt.persistence.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {

	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void findByUserIdOrderByIdDesc() {

		

		List<Post> post = postRepository.findByUserIdOrderByIdDesc(2);

		System.out.println(post.toString());
		
		ResultDto resultDto = new ResultDto(200, "Succeess", post);
		
		System.out.println(resultDto.toString());

	}
	
}
