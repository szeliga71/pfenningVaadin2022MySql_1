package com.example.pfenningvaadin2022mysql_1.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stopp {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private LocalTime ankunftstopp;
    private LocalTime abfahrtstopp;
    private String leergut;
    private long id_markt;
    //private long tour_id;
    //==========================
    private int rollcontainer;
    private int pallette;
    private int dd;
    private int tk_box;
    private int tk_schmall;
    private int m1;
    private int m2;
    private int m4;
    private int blumengross;





}
