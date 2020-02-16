package com.sns.pjt.Service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;

@Service
public interface UserService {

	public User insertUser(User user);

	public User getUserById(int id);

	public User getUserInfoByUsernameAndPassword(User user);

	public Token insertToken(User user, HttpSession session);

	public Boolean overlapUserName(User user);

	Boolean insertEmail(User user) throws Exception;

	Boolean insertIsAdmin(String email, String username);

	public Boolean roleCheck(User user);

}
