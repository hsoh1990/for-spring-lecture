package com.ex.forblog.account;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity(name = "account")
class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 4, max = 15)
    @Column(unique = true, nullable = false)
    private String name;

    @NotNull
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(unique = true, nullable = false)
    private String email;
}
