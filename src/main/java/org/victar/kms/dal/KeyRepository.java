package org.victar.kms.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.victar.kms.models.Key;

@RepositoryRestResource(collectionResourceRel = "keys", path = "api-keys")
public interface KeyRepository extends MongoRepository<Key, String> {

	
}
