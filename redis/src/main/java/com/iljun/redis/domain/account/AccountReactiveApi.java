package com.iljun.redis.domain.account;

import com.iljun.redis.domain.account.dto.AccountCreateDto;
import com.iljun.redis.domain.account.dto.AccountUpdateDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
public class AccountReactiveApi {
    private final AccountReactiveService accountReactiveService;
    public AccountReactiveApi(AccountReactiveService accountReactiveService) {
        this.accountReactiveService = accountReactiveService;
    }

    @GetMapping("/api/reactive/accounts")
    public ResponseEntity<Flux<Account>> findAllAccounts() {
        return ResponseEntity.ok(accountReactiveService.findAll());
    }

    @GetMapping("/api/reactive/account/{id}")
    public Mono<ResponseEntity<Account>> findAccountById(@PathVariable("id") String id) {
        return accountReactiveService.findById(id)
                .map(account -> ResponseEntity.ok(account))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/api/reactive/account")
    public Mono<ResponseEntity> saveAccount(@RequestBody AccountCreateDto accountCreateDto) {
        return this.accountReactiveService.save(accountCreateDto)
                .map(account -> ResponseEntity.created(
                        URI.create(String.format("/api/account/%s", account.getId()))
                ).build());
    }

    @PutMapping("/api/reactive/account/{id}")
    public Mono<ServerResponse> updateAccount(@PathVariable("id") String id,
                                                       @RequestBody AccountUpdateDto accountUpdateDto) {
        return this.accountReactiveService.update(id, accountUpdateDto)
                .map(account -> ServerResponse.ok()
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(account));
    }

}
