package com.sns.pjt.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sns.pjt.Controller.dto.PostDto;
import com.sns.pjt.domain.Post;
import com.sns.pjt.domain.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUserIdOrderByIdDesc(int userId);

	List<Post> findAllByOrderByIdDesc();

	Post findById(int postId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM post WHERE id = :id", nativeQuery = true)
	int deletePost(@Param("id") int postId);

	@Query(value = "select u.id as user_id , u.username, u.created_at , p.*, IF(p.user_id  = :userId ,null,1) "
					+ " from user u join post p on u.id = p.user_id "
					+ "order by p.id desc", nativeQuery = true)
	public List<Post> followPost(@Param("userId") int userId);

}
