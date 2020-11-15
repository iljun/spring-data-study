package com.iljun.mongodb.domain.account.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountUpdateDto {
    private String firstName;
    private String lastName;

}
