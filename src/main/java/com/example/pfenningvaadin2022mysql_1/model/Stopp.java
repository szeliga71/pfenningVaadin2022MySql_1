package com.example.pfenningvaadin2022mysql_1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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




  @ManyToOne//( fetch = FetchType.LAZY)
   @JoinColumn(name = "markt.id")//"id_markt")//,updatable = false,insertable = false)
   @NotNull
   private Markt marktId;


    @ManyToOne
    @JoinColumn(name = "tour.id")
    @NotNull
    private Tour tourId;


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
