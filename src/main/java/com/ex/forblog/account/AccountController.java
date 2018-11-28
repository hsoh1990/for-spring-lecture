package com.ex.forblog.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> registAccount(@RequestBody Account accountDto){
        Account account = accountRepository.save(accountDto);
        ResponseEntity<Account> responseEntity = new ResponseEntity<>(account, HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccount(){
        List<Account> accounts = accountRepository.findAll();
        ResponseEntity<List<Account>> responseEntity = new ResponseEntity<>(accounts, HttpStatus.OK);
        return responseEntity;
    }
}
