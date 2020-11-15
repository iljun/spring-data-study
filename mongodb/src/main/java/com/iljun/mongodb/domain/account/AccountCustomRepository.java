package com.iljun.mongodb.domain.account;

import java.util.List;
import java.util.Optional;

public interface AccountCustomRepository {

    List<Account> findAllByCustom();

    Optional<Account> findByIdByCustom(String id);

    Account saveByCustom(Account account);

    void deleteByCustom(String id);
}
