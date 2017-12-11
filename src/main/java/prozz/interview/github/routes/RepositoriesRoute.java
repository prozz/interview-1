package prozz.interview.github.routes;

import com.google.inject.Inject;
import prozz.interview.github.domain.Repository;
import prozz.interview.github.services.GithubService;
import prozz.interview.github.services.RepositoryNotFoundException;
import spark.Request;
import spark.Response;
import spark.Route;

import static java.lang.String.format;

public class RepositoriesRoute implements Route {

	@Inject
	private GithubService githubService;

	@Override
	public Object handle(Request request, Response response) throws Exception {
		final String owner = request.params(":owner");
		final String repositoryName = request.params(":repositoryName");

		try {
			final Repository repo = githubService.getRepository(owner, repositoryName);
			return ResponseBuilder.toJson(response, 200, repo);
		} catch (RepositoryNotFoundException e) {
			return ResponseBuilder.toErrorJson(response, 404, format("Repository '%s/%s' not found.", e.getOwner(), e.getRepositoryName()));
		}
	}
}
