package prozz.interview.github.urlfetcher;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Ignore
public class UnirestUrlFetcherTest {

	@Test
	public void fetchExistingRepository() throws Exception {
		Response r = new UnirestUrlFetcher().fetch("https://api.github.com/repos/prozz/ogs-notifier");
		assertEquals(200, r.getStatus());
		assertFalse(r.getBody().isEmpty());
	}

	@Test
	public void fetchRepositoryWrongName() throws Exception {
		Response r = new UnirestUrlFetcher().fetch("https://api.github.com/repos/prozz/idontexist");
		assertEquals(404, r.getStatus());
		assertFalse(r.getBody().isEmpty());
	}

	@Test
	public void fetchRepositoryWrongUsername() throws Exception {
		Response r = new UnirestUrlFetcher().fetch("https://api.github.com/repos/idontexist/idontexisteither");
		assertEquals(404, r.getStatus());
		assertFalse(r.getBody().isEmpty());
	}

}
