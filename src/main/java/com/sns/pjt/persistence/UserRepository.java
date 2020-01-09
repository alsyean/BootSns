package com.sns.pjt.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	List<User> findById(int id);
	
	
	 User findByUsernameAndPassword(String username, String password); 

}
