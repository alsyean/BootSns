package com.sns.pjt.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sns.pjt.domain.Follow;

public interface FollowRepository extends CrudRepository<Follow, Integer> {

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM follow WHERE followee_id = :followee", nativeQuery = true)
	int unFollow(@Param("followee") int followee);
	
	@Query(value = "SELECT * FROM follow WHERE follower_id = :follower", nativeQuery = true)
	List<Follow> getFollowerAndFollowee(@Param("follower")int follwer);

}
