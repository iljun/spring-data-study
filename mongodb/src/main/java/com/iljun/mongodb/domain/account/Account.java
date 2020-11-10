package com.iljun.mongodb.domain.account;

import com.iljun.mongodb.domain.account.dto.AccountUpdateDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Document("account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @Getter
    private String id;

    @Getter
    private String firstName;

    @Getter
    private String lastName;

    @Indexed(unique = true)
    @Getter
    private String email;

    @Getter
    @CreatedDate
    private LocalDateTime createdAt;

    @Getter
    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @Builder
    public Account(String firstName,
                   String lastName,
                   String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void updateAccount(AccountUpdateDto accountupdateDto) {
        this.firstName = accountupdateDto.getFirstName();
        this.lastName = accountupdateDto.getLastName();
    }
}
