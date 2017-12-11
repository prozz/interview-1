package prozz.interview.github.services;

import prozz.interview.github.domain.Repository;

public interface GithubService {

	Repository getRepository(String owner, String repositoryName) throws RepositoryNotFoundException;
}
