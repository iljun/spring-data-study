package com.iljun.mongodb.domain.account;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveAccountRepository extends ReactiveMongoRepository<Account, String> {
}
