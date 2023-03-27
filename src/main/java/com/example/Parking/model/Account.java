package com.example.Parking.model;

import com.example.Parking.emu.AcountStatu;
import com.example.Parking.emu.Role;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    //attribute
    @Column(unique = true,nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(name = "dateCreation",nullable = false)
    @DateTimeFormat(pattern = "MM-dd-yy")
    private LocalDate dateCreation;
    @Column(name = "dateConnexion")
    @DateTimeFormat(pattern = "MM-dd-yy")
    private LocalDate lastDateConnexion;


    //emu
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcountStatu statu;

    //assocaition
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "agent_id")
    private Agent agent;

    public Account() {

    }

    public Account(String password, String email, String username, LocalDate dateCreation, LocalDate lastDateConnexion, Role role, AcountStatu statu, User user, Agent agent) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.dateCreation=dateCreation;
        this.lastDateConnexion = lastDateConnexion;
        this.role = role;
        this.statu = statu;
        this.user = user;
        this.agent = agent;
    }
}
