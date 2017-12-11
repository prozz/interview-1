package prozz.interview.github.config;

import com.google.inject.AbstractModule;
import prozz.interview.github.services.GithubService;
import prozz.interview.github.services.GithubServiceImpl;
import prozz.interview.github.services.RandomService;
import prozz.interview.github.services.RandomServiceImpl;
import prozz.interview.github.urlfetcher.UnirestUrlFetcher;
import prozz.interview.github.urlfetcher.UrlFetcher;

public class ProductionConfig extends AbstractModule {

	protected void configure() {
		bind(RandomService.class).to(RandomServiceImpl.class);
		
		bind(UrlFetcher.class).to(UnirestUrlFetcher.class);
		bind(GithubService.class).to(GithubServiceImpl.class);
	}
}
