package com.example.Parking.model;

import lombok.AllArgsConstructor;
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
public class Place implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Attribute
    @Column(name = "Place_name",unique = true)
    private String name;
    //association
    @JoinColumn(name = "agent_id")
    @ManyToOne
    private Agent agent;
    @OneToMany(mappedBy = "place")
    private Collection<Reservation> reservations;

    public Place() {
        reservations= new ArrayList<>();
    }

    public Place(String name, Agent agent) {
        super();
        this.name = name;
        this.agent = agent;
    }

}
