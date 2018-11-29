package com.ex.forblog.account;

import com.ex.forblog.account.AccountDto.AccountRegister;
import com.ex.forblog.account.AccountDto.AccountResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> registAccount(@RequestBody AccountRegister accountDto){
        Account account = accountService.register(accountDto);
        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<List<AccountResponse>> getAllAccount(){
//        List<AccountResponse> accounts = accountService.getAccounts();
//        return new ResponseEntity<>(accounts, HttpStatus.OK);
//    }
}
