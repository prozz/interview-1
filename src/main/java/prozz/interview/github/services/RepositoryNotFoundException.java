package prozz.interview.github.services;

public class RepositoryNotFoundException extends Exception {

	private final String owner;
	private final String repositoryName;

	public RepositoryNotFoundException(String owner, String repositoryName) {
		this.owner = owner;
		this.repositoryName = repositoryName;
	}

	public String getOwner() {
		return owner;
	}

	public String getRepositoryName() {
		return repositoryName;
	}
}
