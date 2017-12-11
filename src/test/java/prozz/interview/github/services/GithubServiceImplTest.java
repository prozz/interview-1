package prozz.interview.github.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prozz.interview.github.domain.Repository;
import prozz.interview.github.urlfetcher.Response;
import prozz.interview.github.urlfetcher.UrlFetcher;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static prozz.interview.github.test.helpers.FilesHelper.readFileAsString;

public class GithubServiceImplTest {

	private GithubServiceImpl service;

	@Mock
	private UrlFetcher urlFetcher;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new GithubServiceImpl(urlFetcher);
	}

	@Test
	public void repoNotFound() throws Exception {
		when(urlFetcher.fetch("https://api.github.com/repos/prozz/ogs-notifier"))
				.thenReturn(new Response(404, "some body describing that repo doesnt exist"));

		try {
			service.getRepository("prozz", "ogs-notifier");
			fail();
		} catch (RepositoryNotFoundException e) {
			assertEquals("prozz", e.getOwner());
			assertEquals("ogs-notifier", e.getRepositoryName());
		}
	}

	@Test
	public void repoFound() throws Exception {
		when(urlFetcher.fetch("https://api.github.com/repos/prozz/ogs-notifier"))
				.thenReturn(new Response(200, readFileAsString("/repo-prozz-ogs-notifier.json")));

		Repository repo = service.getRepository("prozz", "ogs-notifier");
		assertNotNull(repo);
	}

	@Test
	public void repoProperties() throws Exception {
		when(urlFetcher.fetch("https://api.github.com/repos/prozz/ogs-notifier"))
				.thenReturn(new Response(200, readFileAsString("/repo-prozz-ogs-notifier.json")));

		Repository repo = service.getRepository("prozz", "ogs-notifier");
		assertEquals("prozz/ogs-notifier", repo.getFullName());
		assertEquals("Chrome plugin for easy notifications from online-go.com Go server", repo.getDescription());
		assertEquals("https://github.com/prozz/ogs-notifier.git", repo.getCloneUrl());
		assertEquals(12, repo.getStars());
		assertEquals("2013-02-08T11:15:47Z", DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(repo.getCreatedAt()));
	}
}
