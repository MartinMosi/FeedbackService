package com.mosi.feedback.imp;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mosi.feedback.Feedback;
import com.mosi.feedback.FeedbackProvider;
import com.mosi.jpa.FeedbackRepository;
import com.mosi.jpa.entity.FeedbackEntity;

@Service
public class DefaultFeedbackProvider implements FeedbackProvider {

	@Autowired
	private FeedbackRepository repository;

	@Override
	public List<Feedback> getItems() {

		Iterable<FeedbackEntity> entities = repository.findAll();
		List<Feedback> feedbacks = createFeedbacks(entities);
		return feedbacks;
	}

	@Override
	public List<Feedback> getItemsByUser(String user) {

		Iterable<FeedbackEntity> entities = repository.findByUser(user);
		List<Feedback> feedbacks = createFeedbacks(entities);
		return feedbacks;
	}

	@Override
	public Feedback addItem(Feedback feedback) {

		FeedbackEntity newEntity = createFeedbackEntity(feedback);

		newEntity = repository.save(newEntity);

		return createFeedback(newEntity);
	}

	private List<Feedback> createFeedbacks(Iterable<FeedbackEntity> entities) {
		List<Feedback> feedbacks = new ArrayList<>();

		for (FeedbackEntity entity : entities) {
			Feedback feedback = createFeedback(entity);
			feedbacks.add(feedback);
		}

		return feedbacks;
	}

	private Feedback createFeedback(FeedbackEntity feedbackEntity) {
		Feedback feedback = new Feedback();
		feedback.setId(feedbackEntity.getId());
		feedback.setUser(feedbackEntity.getUser());
		feedback.setMessage(feedbackEntity.getMessage());

		ZonedDateTime createDateTime = feedbackEntity.getCreateDate();
		if (createDateTime != null) {
			String createDate = createDateTime.toString();
			feedback.setCreateDate(createDate);
		}
		return feedback;
	}

	private FeedbackEntity createFeedbackEntity(Feedback feedback) {
		FeedbackEntity feedbackEntity = new FeedbackEntity();

		feedbackEntity.setId(feedback.getId());
		feedbackEntity.setUser(feedback.getUser());
		feedbackEntity.setMessage(feedback.getMessage());

		String createDate = feedback.getCreateDate();
		if (createDate != null) {
			ZonedDateTime createDateTime = ZonedDateTime.parse(createDate);
			feedbackEntity.setCreateDate(createDateTime);
		}

		return feedbackEntity;
	}
}
