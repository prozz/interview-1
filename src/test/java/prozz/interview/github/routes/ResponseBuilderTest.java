package prozz.interview.github.routes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prozz.interview.github.test.helpers.Fixtures;
import spark.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class ResponseBuilderTest {

	@Mock
	private Response response;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void toErrorJsonSetsContentType() throws Exception {
		ResponseBuilder.toErrorJson(response, 500, "this is message!");
		verify(response).type("application/json");
	}

	@Test
	public void toErrorJsonSetsHttpStatus() throws Exception {
		ResponseBuilder.toErrorJson(response, 500, "this is message!");
		verify(response).status(500);
	}

	@Test
	public void toErrorJsonContent() throws Exception {
		String result = ResponseBuilder.toErrorJson(response, 500, "this is message!");
		JsonNode asJson = new ObjectMapper().readTree(result);
		assertEquals(500, asJson.get("status").asInt());
		assertEquals("this is message!", asJson.get("message").asText());
	}

	@Test
	public void toJsonSetsContentType() throws Exception {
		ResponseBuilder.toJson(response, 200, Fixtures.defaultRepository());
		verify(response).type("application/json");
	}

	@Test
	public void toJsonSetsHttpStatus() throws Exception {
		ResponseBuilder.toJson(response, 500, Fixtures.defaultRepository());
		verify(response).status(500);
	}

	@Test
	public void toJsonContent() throws Exception {
		String result = ResponseBuilder.toJson(response, 500, Fixtures.defaultRepository());
		JsonNode asJson = new ObjectMapper().readTree(result);
		assertEquals("prozz/ogs-notifier", asJson.get("fullName").asText());
		// just one property to check if valid json was returned, no need for more
	}
}
