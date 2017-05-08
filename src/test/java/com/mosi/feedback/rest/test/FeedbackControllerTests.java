package com.mosi.feedback.rest.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static com.jayway.restassured.RestAssured.*;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mosi.feedback.Feedback;
import com.mosi.jpa.FeedbackRepository;
import com.mosi.jpa.entity.FeedbackEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FeedbackControllerTests {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Before
	public void beforeTest()
	{
		feedbackRepository.save(new FeedbackEntity("karol", "message"));
		feedbackRepository.save(new FeedbackEntity("karol", "message2"));
		feedbackRepository.save(new FeedbackEntity("peter", "Peter's message"));
		feedbackRepository.save(new FeedbackEntity("martin", "Martin's message"));
	}
	
	@Test
	public void getAllFeedbacks() throws Exception {
		given()
		.when()
			.get("/feedback")
		.then()
			.statusCode(200)
			.contentType("application/json")		
			.body("$", hasSize(4));
	}

	@Test
	public void getUserFeedbacks() {
		given()
			.queryParam("user", "karol")
		.when()
			.get("/feedback")
		.then()
			.statusCode(200)
			.contentType("application/json")		
			.body("$", hasSize(2));
		
		given()
			.queryParam("user", "jonas")
		.when()
			.get("/feedback")
		.then()
			.statusCode(200)
			.contentType("application/json")		
			.body("$", hasSize(0));
	}

	@Test
	public void addNewFeedback() throws Exception {
		
		assertEquals(4, feedbackRepository.count());
		
		Feedback newFeedback = new Feedback();
		newFeedback.setUser("karol");
		newFeedback.setMessage("Feedback message");		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonFeedback = mapper.writeValueAsString(newFeedback);
		
		given()
	      .contentType("application/json")
	      .body(jsonFeedback)
	   .when()
	      .post("/feedback")
	   .then()
	      .statusCode(200)
	      .body("id", not(empty()))
	      .body("user", equalTo(newFeedback.getUser()))
	      .body("message", equalTo(newFeedback.getMessage()))
	      .body("createDate", not(empty()));
		
		// It looks like object is stored
		assertEquals(5, feedbackRepository.count());
	}
	
	@Test
	public void AddInvalidUserFeedback() throws Exception {
		assertEquals(4, feedbackRepository.count());
		
		Feedback newFeedback = new Feedback();
		newFeedback.setUser("karol");		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonFeedback = mapper.writeValueAsString(newFeedback);
		
		given()
	      .contentType("application/json")
	      .body(jsonFeedback)
	   .when()
	      .post("/feedback")
	   .then()
	      .statusCode(500);     
		
		// It looks like object is stored
		assertEquals(4, feedbackRepository.count());
	}
	
	@Test
	public void AddInvalidMessageFeedback() throws Exception {
		assertEquals(4, feedbackRepository.count());
		
		Feedback newFeedback = new Feedback();
		newFeedback.setMessage("karol's message");		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonFeedback = mapper.writeValueAsString(newFeedback);
		
		given()
	      .contentType("application/json")
	      .body(jsonFeedback)
	   .when()
	      .post("/feedback")
	   .then()
	      .statusCode(500);     
		
		// It looks like object is stored
		assertEquals(4, feedbackRepository.count());
	}
	
	@After
	public void afterTest()
	{
		// Clean repository
		feedbackRepository.deleteAll();
	}
}
