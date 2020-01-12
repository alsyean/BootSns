package com.sns.pjt.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.TokenRepository;
import com.sns.pjt.persistence.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	public User insertUser(User user) {
 
		return userRepository.save(user); 
	}

	public User getUserById(int id) {

		return userRepository.findById(id);
	}

	@Override
	public User getUserInfoByUsernameAndPassword(User user) {

		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	@Override
	public Token insertToken(User user, HttpSession session) {

		session.setAttribute("userId", user.getId());

		Token token = new Token();
		token.setId(user.getId());
		token.setCreatedAt(new Date());

		return tokenRepository.save(token);
	}

}
