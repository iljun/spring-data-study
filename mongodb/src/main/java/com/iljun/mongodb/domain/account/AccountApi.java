package com.iljun.mongodb.domain.account;

import com.iljun.mongodb.domain.account.dto.AccountCreateDto;
import com.iljun.mongodb.domain.account.dto.AccountUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
public class AccountApi {
    private final AccountRepository accountRepository;
    public AccountApi(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/accounts")
    public ResponseEntity<Collection<Account>> findAllAccounts() {
        return ResponseEntity.ok(accountRepository.findAll());
    }

    @GetMapping("/api/account/{id}")
    public ResponseEntity<Account> findAccountById(@PathVariable("id") String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found Account"));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/api/account")
    public ResponseEntity saveAccount(@RequestBody AccountCreateDto accountCreateDto) {
        Account account = accountCreateDto.toAccount();
        account = accountRepository.save(account);
        return ResponseEntity
                .created(URI.create(String.format("/api/account/%s", account.getId())))
                .build();
    }

    @PutMapping("/api/account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") String id,
                                                 @RequestBody AccountUpdateDto accountUpdateDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        optionalAccount.orElseThrow(() -> new AccountException("not found account"));
        Account account = optionalAccount.get();
        account.updateAccount(accountUpdateDto);
        account = accountRepository.save(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/api/account/{id}")
    public ResponseEntity deleteAccount(@PathVariable("id") String id) {
        accountRepository.findById(id)
                .orElseThrow(() -> new AccountException("not found account"));
        accountRepository.deleteById(id);
        return ResponseEntity
                .accepted()
                .build();
    }
}
