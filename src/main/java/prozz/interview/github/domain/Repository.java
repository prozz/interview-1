package prozz.interview.github.domain;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.ZonedDateTime;

public class Repository {

	private String fullName;
	private String description;
	private String cloneUrl;

	@JsonAlias({"stargazers_count"})
	private int stars;

	private ZonedDateTime createdAt;

	public Repository() {
	}

	public Repository(String fullName, String description, String cloneUrl, int stars, ZonedDateTime createdAt) {
		this.fullName = fullName;
		this.description = description;
		this.cloneUrl = cloneUrl;
		this.stars = stars;
		this.createdAt = createdAt;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}

	public String getCloneUrl() {
		return cloneUrl;
	}

	public int getStars() {
		return stars;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}
}
