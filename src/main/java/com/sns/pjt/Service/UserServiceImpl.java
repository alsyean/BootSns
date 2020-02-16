package com.sns.pjt.Service;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;
import com.sns.pjt.persistence.TokenRepository;
import com.sns.pjt.persistence.UserRepository;
import com.sns.pjt.util.MailHandler;
import com.sns.pjt.util.Role;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenRepository tokenRepository;

	@Autowired
	private JavaMailSender mailSender;

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
	
	public Boolean roleCheck(User user) {
		
		Boolean role = null;
		
		User roleUser = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		
		if(roleUser.getIsAdmin() != null) {
			role = true;
		}else if(roleUser.getIsAdmin() == null){
			role = false;
		}else {
			role = null;
		}
		
		return role;
	}

	@Override
	public Token insertToken(User user, HttpSession session) {

		session.setAttribute("userId", user.getId());

		Token token = new Token();
		token.setId(user.getId());
		token.setCreatedAt(new Date());

		return tokenRepository.save(token);
	}
	
	@Override
	public Boolean overlapUserName(User user) {
		// TODO Auto-generated method stub

		Boolean overlap = null;

		User userCheck;

		userCheck = userRepository.findByUsername(user.getUsername());

		if (userCheck != null) {

			overlap = true;

		} else {

			overlap = false;

		}

		return overlap;
	}
	
	@Override
	public Boolean insertEmail(User user) throws Exception {
		// TODO Auto-generated method stub

		Boolean sendEmail = false;

		MailHandler sendMail = new MailHandler(mailSender);

		try {

			sendMail.setSubject("My Diary에 가입을 축하 드립니다.");
			sendMail.setText(new StringBuffer().append("<h1>" + user.getUsername() + "님 가입을 축하드립니다.</h1>")
					.append("저희 사이트에 가입해주셔서 감사합니다 앞으로 원활한 인증을 위해서 <a href='http://localhost:8080/verify?email="
							+ user.getEmail())
					.append("&username=" + user.getUsername() + "' target='_blenk'>여기를 눌러주세요</a>")
					.append("<input type='hidden' value='" + user.getUsername()).append("'>").toString());
			sendMail.setFrom("alsyean@gmail.com", "My Diary");
			sendMail.setTo(user.getEmail());
			sendMail.send();

			sendEmail = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return sendEmail;
	}
	
	@Override
	public Boolean insertIsAdmin(String email, String username) {
		// TODO Auto-generated method stub

		Boolean isAdmin = null;

		
		User user = userRepository.findByUsername(username);
		
		if(user.getUsername().equals("admin")) {
			user.setIsAdmin(Role.ADMIN);
		}else {
			user.setIsAdmin(Role.MEMBER);	
		}
		

		userRepository.save(user);
		
		
		if (user != null) {
			isAdmin = false;
		}

		return isAdmin;
	}

}
