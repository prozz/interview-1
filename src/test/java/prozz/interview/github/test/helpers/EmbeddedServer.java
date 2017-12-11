package prozz.interview.github.test.helpers;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class EmbeddedServer {

	private static final String TEST_CONTEXT = "/github";
	private static final int TEST_PORT = 8080;
	private static Server server;

	public static String url(String path) {
		return "http://localhost:" + TEST_PORT + TEST_CONTEXT + path;
	}

	public static void startIfRequired() throws Exception {
		if (server == null) {
			System.setProperty("java.naming.factory.url.pkgs", "org.eclipse.jetty.jndi");
			System.setProperty("java.naming.factory.initial", "org.eclipse.jetty.jndi.InitialContextFactory");

			server = new Server(TEST_PORT);

			WebAppContext context = new WebAppContext();
			context.setDescriptor("src/test/webapp/WEB-INF/web.xml");
			context.setResourceBase("src/test/webapp");
			context.setContextPath(TEST_CONTEXT);
			context.setParentLoaderPriority(true);

			server.setHandler(context);

			server.start();
		}
	}

}