package com.iljun.mongodb.domain.account;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

public class AccountCustomRepositoryImpl implements AccountCustomRepository {
    private final MongoTemplate mongoTemplate;
    public AccountCustomRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Account> findAllByCustom() {
        return mongoTemplate.findAll(Account.class);
    }

    @Override
    public Optional<Account> findByIdByCustom(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, Account.class));
    }

    @Override
    public Account saveByCustom(Account account) {
        return mongoTemplate.save(account);
    }

    @Override
    public void deleteByCustom(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAndRemove(query, Account.class);
    }
}
