package prozz.interview.github.test.helpers;

import prozz.interview.github.domain.Repository;

import java.time.ZonedDateTime;

public class Fixtures {

	public static Repository defaultRepository() {
		return new Repository("prozz/ogs-notifier", "Chrome plugin for easy notifications from online-go.com Go server", "https://github.com/prozz/ogs-notifier.git", 12, ZonedDateTime.parse("2013-02-08T11:15:47Z"));
	}
}
