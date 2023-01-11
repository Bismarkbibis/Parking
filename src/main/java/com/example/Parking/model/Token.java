package com.example.Parking.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
public class Token implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token",nullable = false,unique = true)
    private String valeurToken;

    @JsonFormat(pattern = "HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;

    //liaison
    @OneToMany(mappedBy = "token")
    @Column(name = "account_User")
    private Collection<Account> accounts;

    public Token() {
        accounts = new ArrayList<>();
    }

    public Token(String valeurToken, Date expirationDate) {
        this.valeurToken = valeurToken;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
