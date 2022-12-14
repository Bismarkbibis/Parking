package com.example.Parking.model;

import com.example.Parking.emu.AcountStatu;
import com.example.Parking.emu.Role;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    public Account(String password, String email, Role role, AcountStatu statu, User user, Agent agent) {
        this.password = password;
        this.email = email;
        this.role = role;
        this.statu = statu;
        this.user = user;
        this.agent = agent;
    }
}
