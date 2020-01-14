package com.sns.pjt.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sns.pjt.domain.Feed;

public interface FeedRepository extends CrudRepository<Feed, Integer> {

	@Modifying
	@Transactional
	@Query(value = "delete from feed where followee_id = :followee", nativeQuery=true)
	public int deleteFeed(@Param("followee") int followee);


}
