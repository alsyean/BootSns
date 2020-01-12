package com.sns.pjt;

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
import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.TokenRepository;
import com.sns.pjt.persistence.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	User user = new User();

	//@Before 
	@Test
	public void testInserUser() {

		user.setUsername("yy");
		user.setPassword("yy");

		userRepository.save(user);

	}

	@Test
	@Ignore
	public void testInsertToken() {

		System.out.println("실행 전 ");

		Token token = new Token();
		token.setId(user.getId());

		tokenRepository.save(token);

		System.out.println("token 실행 후 ");

	}

	@Test
	@Ignore
	public void testSelectUsernameAndPassword() {

		User result = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());

		System.out.println("result : " + result.toString());
	}

	@Test
	@Ignore
	public void testFindById() {
		User result = userRepository.findById(1);

		System.out.println("result : " + result);
	}

}
