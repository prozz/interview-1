package prozz.interview.github.routes;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mockito;
import prozz.interview.github.test.helpers.EmbeddedServer;

import static prozz.interview.github.config.MockConfigModule.githubService;
import static prozz.interview.github.config.MockConfigModule.randomService;

public abstract class AbstractRouteTest {

	@BeforeClass
	public static void startServer() throws Exception {
		EmbeddedServer.startIfRequired();
	}

	@Before
	public void resetMocks() {
		Mockito.reset(randomService, githubService);
	}

	protected GetRequest get(String url) {
		return Unirest.get(url);
	}

}
