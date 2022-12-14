package com.example.Parking.model;


import com.example.Parking.emu.CarType;
import com.example.Parking.emu.ParkingCategory;
import com.example.Parking.emu.ParkingService;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
public class Parking implements Serializable {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //attribute
    @Column(nullable = false)
    private String name;
    @Temporal(TemporalType.TIME)
    @Column(name = "Open_Time",nullable = false)
    private Date open;
    @Column(nullable = false)
    private String caracteristique;
    @Column(nullable = false)
    private String information;
    @Column(nullable = false)
    private String Description;
    @Column(nullable = false,name = "Place_handicap")
    private boolean handicap;
    @Column(nullable = false)
    private String images;

    //emu
    @Enumerated(EnumType.STRING)
    @Column(name = "type_car",nullable = false)
    private CarType carType;
    @Enumerated(EnumType.STRING)
    @Column(name = "Category_Parking",nullable = false)
    private ParkingCategory category;
    @Enumerated(EnumType.STRING)
    @Column(name = "Parking_Setvice")
    private ParkingService parkingService;


    //association
    @JoinColumn(name = "adress_id")
    @ManyToOne
    private Adress adress;
    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;


}
