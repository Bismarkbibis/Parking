package com.example.Parking.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Getter
@Setter
@Entity
public class Adress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //proprieter
    @Column(nullable = false)
    private String numVoie;
    @Column(nullable = false)
    private String nameVoie;
    @Column(nullable = false)
    private String typeVoie;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String city;

    //association
    @OneToMany(mappedBy = "adress",cascade = CascadeType.ALL)
    private Collection<User> users;
    @OneToMany(mappedBy = "adress",cascade = CascadeType.ALL)
    private Collection<Parking> parkings;
    @OneToMany(mappedBy = "adress",cascade = CascadeType.ALL)
    private Collection<Agent> agents;
 
    public Adress() {
        users = new ArrayList<>();
        parkings = new ArrayList<>();
        agents= new ArrayList<>();
    }

    public Adress(String numVoie, String nameVoie, String typeVoie, String postalCode, String city) {
        super();
        this.numVoie = numVoie;
        this.nameVoie = nameVoie;
        this.typeVoie = typeVoie;
        this.postalCode = postalCode;
        this.city = city;
    }
}
