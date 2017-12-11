package prozz.interview.github.routes;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.json.JSONObject;
import org.junit.Test;
import prozz.interview.github.services.RepositoryNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static prozz.interview.github.config.MockConfigModule.githubService;
import static prozz.interview.github.test.helpers.EmbeddedServer.url;
import static prozz.interview.github.test.helpers.Fixtures.defaultRepository;

public class RepositoriesRouteTest extends AbstractRouteTest {

	@Test
	public void repoNotFound() throws Exception {
		when(githubService.getRepository("prozz", "ogs-notifier")).thenThrow(new RepositoryNotFoundException("prozz", "ogs-notifier"));
		HttpResponse<JsonNode> r = get(url("/repositories/prozz/ogs-notifier")).asJson();

		assertTrue(r.getHeaders().get("Content-Type").contains("application/json"));
		assertEquals(404, r.getStatus());
		assertEquals(404, r.getBody().getObject().getInt("status"));
		assertEquals("Repository 'prozz/ogs-notifier' not found.", r.getBody().getObject().getString("message"));
	}

	@Test
	public void repoExist() throws Exception {
		when(githubService.getRepository("prozz", "ogs-notifier")).thenReturn(defaultRepository());
		HttpResponse<JsonNode> r = get(url("/repositories/prozz/ogs-notifier")).asJson();

		assertTrue(r.getHeaders().get("Content-Type").contains("application/json"));
		assertEquals(200, r.getStatus());

		final JSONObject json = r.getBody().getObject();
		assertNotNull(json);

		assertEquals("prozz/ogs-notifier", json.getString("fullName"));
		assertEquals("Chrome plugin for easy notifications from online-go.com Go server", json.getString("description"));
		assertEquals("https://github.com/prozz/ogs-notifier.git", json.getString("cloneUrl"));
		assertEquals(12, json.getInt("stars"));
		assertEquals("2013-02-08T11:15:47Z", json.getString("createdAt"));
	}

}
