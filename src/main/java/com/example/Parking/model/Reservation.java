package com.example.Parking.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    //Attribute
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yy")
    @Column(name = "begin_Date",nullable = false)
    private Date begin;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM-dd-yy")
    @Column(name = "ending_Date",nullable = false,insertable = false,updatable = false)
    private Date end;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "MM-dd-yy")
    @Column(name = "begin_Date",nullable = false,insertable = false,updatable = false)
    private Date arrive;

    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "MM-dd-yy")
    @Column(name = "departure_Date")
    private Date departure;

    @Column(name = "number")
    private String number;

    //association
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "place_id")
    @ManyToOne
    private Place place;

    public Reservation() {
    }
}
