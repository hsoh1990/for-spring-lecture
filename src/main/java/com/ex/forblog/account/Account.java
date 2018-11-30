package com.ex.forblog.account;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
class Account {
    Account(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    private int id;

    private String name;

    private String password;

    private String email;
}
