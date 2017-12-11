package prozz.interview.github.services;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

	public int getInteger() {
		return new Random().nextInt();
	}
}
