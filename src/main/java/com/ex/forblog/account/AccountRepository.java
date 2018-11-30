package com.ex.forblog.account;

import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
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
        if (account.getId() == 0){
            account.setId(accounts.size()+1);
            accounts.add(account);
            accountId += 1;
        } else {
            accounts.set(account.getId()-1, account);
        }

        return account;
    }

    public List<Account> findAll() {
        return accounts;
    }

    public Account findById(int id) {
        Account account = accounts.stream()
                .filter(act -> Integer.valueOf(id).equals(act.getId()))
                .findAny()
                .orElse(null);
        return account;
    }

    public void delete(Account account) {
        accounts.remove(account.getId()-1);
    }
}
