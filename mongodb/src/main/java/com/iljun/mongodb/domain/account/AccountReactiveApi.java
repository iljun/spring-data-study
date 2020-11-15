package com.iljun.mongodb.domain.account;

import com.iljun.mongodb.domain.account.dto.AccountCreateDto;
import com.iljun.mongodb.domain.account.dto.AccountUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AccountReactiveApi {
    private ReactiveAccountRepository reactiveAccountRepository;
    public AccountReactiveApi(ReactiveAccountRepository reactiveAccountRepository) {
        this.reactiveAccountRepository = reactiveAccountRepository;
    }

    @GetMapping("/api/reactive/accounts")
    public Flux<Account> getAllCounts() {
        return this.reactiveAccountRepository.findAll();
    }

    @GetMapping("/api/reactive/account/{id}")
    public Mono<ResponseEntity<Account>> getAccount(@PathVariable("id") String id) {
        return this.reactiveAccountRepository
                .findById(id)
                .map(account -> ResponseEntity.ok(account))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/reactive/account")
    public Mono<ResponseEntity<Account>> createAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return this.reactiveAccountRepository
                .save(accountCreateDto.toAccount())
                .map(account -> ResponseEntity.ok(account));
    }

    @PutMapping("/api/reactive/account/{id}")
    public Mono<ResponseEntity<Account>> updateAccount(@PathVariable("id") String id,
                                                       @RequestBody AccountUpdateDto accountUpdateDto) {
        return this.reactiveAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new AccountException("not found account")))
                .flatMap(account -> {
                    account.updateAccount(accountUpdateDto);
                    return this.reactiveAccountRepository.save(account);
                })
                .map(updateAccount -> ResponseEntity.ok(updateAccount));
    }

    @DeleteMapping("/api/reactive/account/{id}")
    public Mono<ResponseEntity> deleteAccount(@PathVariable("id") String id) {
        return this.reactiveAccountRepository.findById(id)
                .switchIfEmpty(Mono.error(new AccountException("not found account")))
                .flatMap(account -> this.reactiveAccountRepository.delete(account).then(Mono.just(ResponseEntity.accepted().build())));
    }
}
