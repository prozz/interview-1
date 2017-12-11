package prozz.interview.github.config;

import com.google.inject.AbstractModule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import prozz.interview.github.services.GithubService;
import prozz.interview.github.services.RandomService;

public class MockConfigModule extends AbstractModule {

	@Mock
	public static RandomService randomService;

	@Mock
	public static GithubService githubService;

	@Override
	protected void configure() {
		MockitoAnnotations.initMocks(this);
		bind(RandomService.class).toInstance(randomService);
		bind(GithubService.class).toInstance(githubService);
	}

}
