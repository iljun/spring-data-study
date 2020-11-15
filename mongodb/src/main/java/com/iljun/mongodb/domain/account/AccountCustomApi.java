package com.iljun.mongodb.domain.account;

import com.iljun.mongodb.domain.account.dto.AccountCreateDto;
import com.iljun.mongodb.domain.account.dto.AccountUpdateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AccountCustomApi {
    private final AccountRepository accountRepository;
    public AccountCustomApi(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/api/custom-accounts")
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountRepository.findAllByCustom());
    }

    @GetMapping("/api/custom-account/{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") String id) {
        Account account = accountRepository.findByIdByCustom(id)
                .orElseThrow(() -> new AccountException("not found account"));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/api/custom-account")
    public ResponseEntity save(@RequestBody AccountCreateDto accountCreateDto) {
        Account account = accountRepository.saveByCustom(accountCreateDto.toAccount());
        return ResponseEntity
                .created(URI.create(String.format("/api/account/custom/%s", account.getId())))
                .build();
    }

    @PutMapping("/api/custom-account/{id}")
    public ResponseEntity<Account> update(@PathVariable("id") String id,
                                          @RequestBody AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findByIdByCustom(id)
                .orElseThrow(() -> new AccountException("not found account"));
        account.updateAccount(accountUpdateDto);
        account = accountRepository.saveByCustom(account);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/api/custom-account/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        accountRepository.deleteByCustom(id);
        return ResponseEntity.accepted().build();
    }
}
