package prozz.interview.github.test.helpers;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FilesHelper {

	public static byte[] readFile(String path) throws Exception {
		final URL resource = FilesHelper.class.getResource(path);
		return Files.readAllBytes(Paths.get(resource.toURI()));
	}

	public static String readFileAsString(String path) throws Exception {
		return new String(readFile(path));
	}

}
