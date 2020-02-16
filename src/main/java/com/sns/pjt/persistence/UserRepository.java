package com.sns.pjt.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sns.pjt.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value = "SELECT * FROM user u WHERE u.id = ?1", nativeQuery = true)
	User findById(@Param("id") int id);

	User findByUsernameAndPassword(String username, String password);
	
	User findByUsername(String username);
	
	

}
