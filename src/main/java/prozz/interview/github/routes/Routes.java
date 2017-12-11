package prozz.interview.github.routes;

import com.google.inject.Inject;

import static spark.Spark.get;

public class Routes {

	@Inject
	private RandomRoute randomRoute;
	@Inject
	private RepositoriesRoute repositoriesRoute;

	public void setup() {
		get("/random", randomRoute);
		get("/repositories/:owner/:repositoryName", repositoriesRoute);
	}
}
