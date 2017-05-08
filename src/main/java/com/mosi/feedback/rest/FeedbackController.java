package com.mosi.feedback.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mosi.error.InvalidInputException;
import com.mosi.feedback.Feedback;
import com.mosi.feedback.FeedbackProvider;

/**
 * Feedback end point which provides action around feedback records.
 * 
 * 
 * @author Martin
 *
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

	private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

	@Autowired
	private FeedbackProvider feedbackProvider;

	public FeedbackController() {
		logger.debug("Initializing FeedbackController");
	}

	/**
	 * Returns all feedback objects maintained by this service
	 * 
	 * @param user
	 *            optional user name query parameter. If specified returns all
	 *            feedbacks with the user name
	 * @return feedback objects
	 */
	@RequestMapping(method = RequestMethod.GET)
	public List<Feedback> getFeedbacks(@RequestParam Optional<String> user) {

		List<Feedback> results = null;

		if (user.isPresent()) {
			results = feedbackProvider.getItemsByUser(user.get());

			if (logger.isDebugEnabled()) {
				logger.debug("Response with feedbacks for user " + user.get() + ":\n" + results);
			}

		} else {
			results = feedbackProvider.getItems();

			if (logger.isDebugEnabled()) {
				logger.debug("Response with all feedbacks:\n" + results);
			}

		}

		return results;
	}

	/**
	 * Add new feedback object to the service data store
	 * 
	 * @param feedback input request body JSON object
	 * @return feedback object
	 * @throws InvalidInputException throws exception if input attributes are invalid
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Feedback addFeedback(@RequestBody Feedback feedback) throws InvalidInputException {

		if (logger.isDebugEnabled()) {
			logger.debug("Request to add feedback: " + feedback);
		}

		if (StringUtils.isEmpty(feedback.getUser())) {
			throw new InvalidInputException(
					"Missing 'user' attribute in request body object. It's not specificed or null");
		}
		if (StringUtils.isEmpty(feedback.getMessage())) {
			throw new InvalidInputException(
					"Missing 'message' attribute in request body object. It's not specificed or null");
		}

		Feedback result = feedbackProvider.addItem(feedback);

		if (logger.isDebugEnabled()) {
			logger.debug("Response with feedback: " + result);
		}

		return result;
	}
}
