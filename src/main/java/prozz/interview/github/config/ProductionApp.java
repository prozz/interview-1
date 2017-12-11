package prozz.interview.github.config;

import prozz.interview.github.App;
import spark.servlet.SparkApplication;

public class ProductionApp implements SparkApplication {

	@Override
	public void init() {
		new App(new ProductionConfig()).init();
	}
}
