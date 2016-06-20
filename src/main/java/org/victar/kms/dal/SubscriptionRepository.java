package org.victar.kms.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.victar.kms.models.Subscription;

@RepositoryRestResource(collectionResourceRel = "subscriptions", path = "api-subscriptions")
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

}
