package com.sns.pjt.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sns.pjt.domain.Token;
import com.sns.pjt.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user u WHERE u.id = ?1", nativeQuery = true)
	User findById(@Param("id") int id);

	User findByUsernameAndPassword(String username, String password);
	
	

}
