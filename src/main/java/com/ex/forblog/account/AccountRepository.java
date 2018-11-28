package com.ex.forblog.account;


import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AccountRepository {

    private List<Account> accounts = new ArrayList();
    private int accountId;


    AccountRepository() {
        Account account = new Account("hsoh","pass", "hsoh@mail.com");
        account.setId(1);
        accounts.add(account);
        accountId = 2;
    }

    public Account save(Account accountDto) {
        accountDto.setId(accountId);
        accounts.add(accountDto);
        Account account = accounts.get(accountId-1);
        accountId += 1;
        return account;
    }

    public List<Account> findAll(){
        return accounts;
    }
}
