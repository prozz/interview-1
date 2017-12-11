package prozz.interview.github.config;

import prozz.interview.github.App;
import spark.servlet.SparkApplication;

public class TestApp implements SparkApplication {

	public void init() {
		new App(new MockConfigModule()).init();
	}
}