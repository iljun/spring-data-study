package com.iljun.redis.domain.account;

import com.iljun.redis.domain.account.dto.AccountCreateDto;
import com.iljun.redis.domain.account.dto.AccountUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountApi {
    private AccountRepository accountRepository;
    public AccountApi(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/accounts")
    public ResponseEntity<List<Account>> findAllAccounts() {
        List<Account> accounts = (List<Account>) accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/api/account/{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found account"));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/api/account")
    public ResponseEntity<Account> saveAccount(@RequestBody @Validated AccountCreateDto accountCreateDto) {
        Account account = accountCreateDto.toAccount();
        account = accountRepository.save(account);
        return ResponseEntity
                .created(URI.create(String.format("/api/account/%s", account.getId())))
                .build();
    }

    @PutMapping("/api/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String id,
                                                 @RequestBody @Validated AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found account"));
        account.updateAccount(accountUpdateDto);
        account = accountRepository.save(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/api/account/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found Account"));
        accountRepository.delete(account);
        return ResponseEntity.accepted().build();
    }
}
