package prozz.interview.github;

import prozz.interview.github.config.ProductionConfig;

public class AppRunner {

	public static void main(String[] args) {
		new App(new ProductionConfig()).init();
	}
}
