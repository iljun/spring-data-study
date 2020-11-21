package com.iljun.redis.domain.account;

import com.iljun.redis.domain.account.dto.AccountCreateDto;
import com.iljun.redis.domain.account.dto.AccountUpdateDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountReactiveService {
    private final AccountReactiveRepository accountReactiveRepository;
    public AccountReactiveService(AccountReactiveRepository accountReactiveRepository) {
        this.accountReactiveRepository = accountReactiveRepository;
    }

    public Flux<Account> findAll() {
        return Flux.fromIterable(this.accountReactiveRepository.findAll());
    }

    public Mono<Account> findById(String id) {
        return Mono.just(this.accountReactiveRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found account")));
    }

    public Mono<Account> save(AccountCreateDto accountCreateDto) {
        return Mono.just(this.accountReactiveRepository.save(accountCreateDto.toAccount()));
    }

    public Mono<Account> update(String id, AccountUpdateDto accountUpdateDto) {
        return Mono.just(this.accountReactiveRepository.findById(id))
                .switchIfEmpty(Mono.error(new AccountException("not found account")))
                .map(account -> {
                    return account.get();
                })
                .map(account -> {
                    account.updateAccount(accountUpdateDto);
                    return account;
                })
                .map(account -> this.accountReactiveRepository.save(account))
                .doOnError(throwable -> new AccountException("unexpected exception"))
                .log();
    }
}
