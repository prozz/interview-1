package prozz.interview.github.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.inject.Inject;
import prozz.interview.github.domain.Repository;
import prozz.interview.github.urlfetcher.Response;
import prozz.interview.github.urlfetcher.UrlFetcher;

import java.io.IOException;

import static java.lang.String.format;

public class GithubServiceImpl implements GithubService {

	private final UrlFetcher urlFetcher;

	@Inject
	public GithubServiceImpl(UrlFetcher urlFetcher) {
		this.urlFetcher = urlFetcher;
	}

	@Override
	public Repository getRepository(String owner, String repositoryName) throws RepositoryNotFoundException {
		Response response = urlFetcher.fetch(format("https://api.github.com/repos/%s/%s", owner, repositoryName));
		if (response.getStatus() == 404) {
			throw new RepositoryNotFoundException(owner, repositoryName);
		}

		if (response.getStatus() == 200) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
				mapper.registerModule(new JavaTimeModule());
				return mapper.readValue(response.getBody(), Repository.class);
			} catch (IOException e) {
				throw new IllegalStateException("Cannot handle 200 response, invalid format? " + response.getBody(), e);
			}
		}

		throw new IllegalStateException("Cannot handle response with HTTP code " + response.getStatus() + " and body: " + response.getBody());
	}
}
