package prozz.interview.github.routes;

import com.mashape.unirest.http.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static prozz.interview.github.config.MockConfigModule.randomService;
import static prozz.interview.github.test.helpers.EmbeddedServer.url;

public class RandomRouteTest extends AbstractRouteTest {

	@Test
	public void getInt() throws Exception {
		when(randomService.getInteger()).thenReturn(666);
		HttpResponse<String> res = get(url("/random")).asString();
		assertEquals(200, res.getStatus());
		assertEquals("666", res.getBody());
	}
}