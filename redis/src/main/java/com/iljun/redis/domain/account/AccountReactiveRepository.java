package com.iljun.redis.domain.account;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountReactiveRepository extends KeyValueRepository<Account, String> {
}
