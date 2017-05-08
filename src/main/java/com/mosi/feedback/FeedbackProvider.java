package com.mosi.feedback;

import java.util.List;

/**
 * Feedback objects provider. This layer separates REST API from data layer. It
 * allows us to replace persistent layer if needed.
 * 
 * @author Martin
 *
 */
public interface FeedbackProvider {

	/**
	 * Get all feedback items.
	 * 
	 * @return list of feedbacks
	 */
	List<Feedback> getItems();

	/**
	 * Get all items which have the same user name
	 * 
	 * @param user
	 *            specified user name
	 * @return list of feedbacks
	 */
	List<Feedback> getItemsByUser(String user);

	/**
	 * Add new feedback item to the store
	 * 
	 * @param feedback
	 *            feedback object which needs to be stored
	 * 
	 * @return stored feedback object
	 */
	Feedback addItem(Feedback feedback);
}
