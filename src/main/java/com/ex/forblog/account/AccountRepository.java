package com.ex.forblog.account;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {

    private List<Account> accounts = new ArrayList<Account>();
    private int accountId;


    AccountRepository() {
        accountId = 1;
    }

    public Account save(Account account) {
        account.setId(accountId);
        accounts.add(account);
        accountId += 1;
        return account;
    }

//    public List<Account> findAll(){
//        return accounts;
//    }

}
