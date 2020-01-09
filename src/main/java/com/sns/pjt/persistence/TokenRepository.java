package com.sns.pjt.persistence;

import org.springframework.data.repository.CrudRepository;

import com.sns.pjt.domain.Token;


public interface TokenRepository extends CrudRepository<Token, String> {

}
