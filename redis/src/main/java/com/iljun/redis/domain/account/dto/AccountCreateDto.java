package com.iljun.redis.domain.account.dto;

import com.iljun.redis.domain.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountCreateDto {
    private String firstName;
    private String lastName;
    private String email;

    public Account toAccount() {
        return Account.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .email(this.email)
                .build();
    }
}
