package prozz.interview.github.routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import spark.Response;

public class ResponseBuilder {

	public static String toErrorJson(Response response, int status, String message) {
		response.type("application/json");
		response.status(status);

		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("status", status);
		node.put("message", message);
		return node.toString();
	}

	public static String toJson(Response response, int status, Object obj) {
		response.type("application/json");
		response.status(status);

		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

}
