package com.ex.forblog.account;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("squid:S1118")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {
    @Data
    public static class AccountRegister {
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotBlank
        private String email;
    }

    @Data
    public static class AccountResponse {
        private int id;
        private String name;
        private String email;
    }
}
