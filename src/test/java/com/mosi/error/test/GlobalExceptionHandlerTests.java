package com.mosi.error.test;

import static com.jayway.restassured.RestAssured.given;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GlobalExceptionHandlerTests {

	@Test
	public void testUnknownException() throws Exception {
		given()
		.when()
			.post("/feedback")
		.then()
			.statusCode(500)
			.contentType("application/json");			
	}
	
	@Test
	public void testNoHandlerFoundException() throws Exception {
		given()
		.when()
			.get("/feedbackroung")
		.then()
			.statusCode(404)
			.contentType("application/json");
	}
}
