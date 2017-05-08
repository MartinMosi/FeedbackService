package com.mosi.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mosi.jpa.entity.FeedbackEntity;

/**
 * Simple JPA Repository for storing feedback records.
 * 
 * @author Martin
 *
 */
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Integer> {

	/**
	 * Find all feedback records with matching user name
	 * 
	 * @param user
	 *            Specified user name
	 * @return list of feedbacks
	 */
	List<FeedbackEntity> findByUser(String user);
}
