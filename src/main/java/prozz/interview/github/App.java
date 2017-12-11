package prozz.interview.github;

import com.google.inject.Guice;
import com.google.inject.Module;
import prozz.interview.github.routes.Routes;
import spark.servlet.SparkApplication;

public class App implements SparkApplication {

	private final Module configModule;

	public App(Module configModule) {
		this.configModule = configModule;
	}

	public void init() {
		Routes routes = new Routes();
		Guice.createInjector(this.configModule).injectMembers(routes);
		routes.setup();
	}
}
