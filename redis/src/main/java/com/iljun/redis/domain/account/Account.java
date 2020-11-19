package com.iljun.redis.domain.account;

import com.iljun.redis.domain.account.dto.AccountUpdateDto;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@RedisHash("account")
@Getter
public class Account implements Serializable {

    @Indexed
    private String id;

    private String firstName;

    private String lastName;

    private String email;

    @Builder
    public Account(String firstName,
                   String lastName,
                   String email) {
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void updateAccount(AccountUpdateDto accountupdateDto) {
        this.firstName = accountupdateDto.getFirstName();
        this.lastName = accountupdateDto.getLastName();
    }
}
