package com.ex.forblog.account;

import com.ex.forblog.account.AccountDto.AccountRegistDto;
import com.ex.forblog.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ModelMapper modelMapper;

    public Account register(AccountRegistDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public Account getAccount(int id) {
        Account account = accountRepository.findById(id);
        if (account ==null){
            throw new NotFoundException(String.valueOf(id));
        }
        return account;
    }

    public Account updateAccount(int id, AccountDto.AccountUpdateDto accountUpdateDto) {
        Account account = accountRepository.findById(id);
        if (account ==null){
            throw new NotFoundException(String.valueOf(id));
        }

        account.setEmail(accountUpdateDto.getEmail());
        accountRepository.save(account);

        return account;
    }

    public Account deleteAccount(int id) {
        Account account = accountRepository.findById(id);
        if (account ==null){
            throw new NotFoundException(String.valueOf(id));
        }

        accountRepository.delete(account);
        return account;
    }
}
