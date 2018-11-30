package com.ex.forblog.account;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("squid:S1118")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountRegistDto {
        @NotBlank
        private String name;
        @NotBlank
        private String password;
        @NotBlank
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountUpdateDto {
        @NotBlank
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccountResponseDto {
        private int id;
        private String name;
        private String email;
    }
}
