package com.example.Parking.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Token implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = true,unique = true)
    private String valeurToken;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateExpire;

    @ManyToOne
    private Account account;

    public Token(String valeurToken, Date dateExpire) {
        this.valeurToken = valeurToken;
        this.dateExpire = dateExpire;
    }

    public Token(String valeurToken, Date dateExpire, Account account) {
        this.valeurToken = valeurToken;
        this.dateExpire = dateExpire;
        this.account = account;
    }

    public Token() {

    }
}
