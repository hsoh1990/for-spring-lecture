package com.ex.forblog.account;

import com.ex.forblog.account.AccountDto.AccountRegistDto;
import com.ex.forblog.account.AccountDto.AccountResponseDto;
import com.ex.forblog.exception.ExceptionDto;
import com.ex.forblog.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registAccount(@RequestBody @Valid final AccountRegistDto accountDto,
                                        final BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
        }

        Account account = accountService.register(accountDto);
        AccountResponseDto accountResponseDto = modelMapper.map(account, AccountResponseDto.class);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        List<AccountResponseDto> accountResponsDtos = accounts.stream()
                .map(account -> modelMapper.map(account, AccountResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(accountResponsDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getAccount(@PathVariable int id) {
        Account account = accountService.getAccount(id);
        AccountResponseDto accountResponseDto = modelMapper.map(account, AccountResponseDto.class);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateAccount(@PathVariable int id, @RequestBody AccountDto.AccountUpdateDto accountUpdateDto) {
        Account account = accountService.updateAccount(id, accountUpdateDto);
        AccountResponseDto accountResponseDto = modelMapper.map(account, AccountResponseDto.class);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity deleteAccount(@PathVariable int id){
        Account account = accountService.deleteAccount(id);
        AccountResponseDto accountResponseDto = modelMapper.map(account, AccountResponseDto.class);
        return new ResponseEntity<>(accountResponseDto, HttpStatus.OK);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity accountNotfoundException(NotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("id가 " + e.getMessage() + "인 계정이 없습니다.");
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

}
