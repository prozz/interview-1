package prozz.interview.github.routes;

import com.google.inject.Inject;
import prozz.interview.github.services.RandomService;
import spark.Request;
import spark.Response;
import spark.Route;

public class RandomRoute implements Route {

	@Inject
	private RandomService randomService;

	public Object handle(Request request, Response response) {
		return randomService.getInteger();
	}
}
