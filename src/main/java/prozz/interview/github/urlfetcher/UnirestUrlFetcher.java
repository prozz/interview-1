package prozz.interview.github.urlfetcher;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestUrlFetcher implements UrlFetcher {

	@Override
	public Response fetch(String url) {
		try {
			HttpResponse<String> r = Unirest.get(url).asString();
			return new Response(r.getStatus(), r.getBody());
		} catch (UnirestException e) {
			throw new RuntimeException(e);
		}
	}

}
