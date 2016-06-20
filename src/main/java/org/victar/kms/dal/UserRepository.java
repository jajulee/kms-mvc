package org.victar.kms.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.victar.kms.models.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "api-users")
public interface UserRepository extends MongoRepository<User, String> {
	

}
