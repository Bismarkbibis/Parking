package com.example.Parking.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Agent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Attribut
    @Column(name = "agent_name",nullable = false)
    private String name;
    @Column(unique = true,nullable = false)
    private String number;
    @Column(unique = true,nullable = false)
    private String siretNumber;
    @Column(unique = true,nullable = false)
    private String email;

    //association
    @ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "adress_id")
    private Adress adress;
    @OneToMany(mappedBy = "agent")
    private Collection<Place> places;
    @OneToMany(mappedBy = "agent")
    private Collection<Parking> parkings;
    @OneToMany(mappedBy = "agent",cascade=CascadeType.ALL)
    private Collection<Account> accounts;


    public Agent() {
        places = new ArrayList<>();
        parkings = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    public Agent(String name, String number, String siretNumber, String email, Adress adress) {
        super();
        this.name = name;
        this.number = number;
        this.siretNumber = siretNumber;
        this.email = email;
        this.adress = adress;
    }
}
