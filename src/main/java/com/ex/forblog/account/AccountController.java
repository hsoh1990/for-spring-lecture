package com.ex.forblog.account;

import com.ex.forblog.account.AccountDto.AccountRegister;
import com.ex.forblog.account.AccountDto.AccountResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registAccount(@RequestBody @Valid final AccountRegister accountDto,
                                                         final BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>( result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        Account account = accountService.register(accountDto);
        AccountResponse accountResponse = modelMapper.map(account, AccountResponse.class);
        return new ResponseEntity<>(accountResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllAccount(){
        AccountResponse accountResponse = AccountResponse.builder().id(2).name("hsoh").email("h@g.com").build();
        List<AccountResponse> accounts = Arrays.asList(accountResponse);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
}
