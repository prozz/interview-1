package prozz.interview.github.integration;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import prozz.interview.github.App;
import prozz.interview.github.config.ProductionConfig;
import spark.Spark;

import static com.mashape.unirest.http.Unirest.get;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class IntegrationTest {

	@BeforeClass
	public static void setUp() throws Exception {
		new App(new ProductionConfig()).init();
		Spark.awaitInitialization();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Spark.stop();
	}

	@Test
	public void repoExists() throws Exception {
		HttpResponse<JsonNode> response = get("http://localhost:4567/repositories/prozz/ogs-notifier").asJson();
		assertEquals(200, response.getStatus());
		assertEquals("prozz/ogs-notifier", response.getBody().getObject().getString("fullName"));
	}

	@Test
	public void repoNotFound() throws Exception {
		HttpResponse<JsonNode> response = get("http://localhost:4567/repositories/idontexist/idontexisteither").asJson();
		assertEquals(404, response.getStatus());
		assertEquals(404, response.getBody().getObject().getInt("status"));
		assertFalse(response.getBody().getObject().getString("message").isEmpty());
	}
}
