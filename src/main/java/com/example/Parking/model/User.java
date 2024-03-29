package com.example.Parking.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Bismark
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Getter
@Setter
@Table(name = "Customer")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //priority
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false,unique = true)
    private String number;
    @Column(nullable = false,unique = true)
    private String email;

    //association
    @OneToMany(mappedBy = "user")
    private Collection<Reservation> reservations;
    @OneToMany(mappedBy = "user")
    private Collection<Account> accounts;
    @ManyToOne
    @JoinColumn(name = "adresse_id")
    private Adress adress;


    public User() {
        reservations = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public User(String name, String firstName, String number, String email, Adress adress) {
        super();
        this.name = name;
        this.firstName = firstName;
        this.number = number;
        this.email = email;
        this.adress = adress;
    }

}
