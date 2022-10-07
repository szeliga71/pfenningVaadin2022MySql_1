package com.example.pfenningvaadin2022mysql_1.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tour {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalTime abfahrtlager;
    private LocalTime ankunftlager;

   /* @ManyToOne
    @JoinColumn(name="arbeit_tag.id")
    @NotNull
    @JsonIgnoreProperties({"touren"})
    private ArbeitTag arbeit_tag_id;*/

    private long arbeit_tag_id;

    @ManyToOne
    @JoinColumn(name = "lkw.kenz")
    @NotNull
    private Lkw lkw_kenz;



   /*@JoinTable(name="lkw")
   @NotNull
   private String lkw_kenz;*/
    private int rewe_kilometer ;
    private int tour_kilometer;
    private String tour_nr;

    /*@OneToMany(mappedBy="tour_id") //(cascade = CascadeType.REMOVE)
    //@JoinColumn(name="tour_id",updatable = false,insertable = false)
    private List<Stopp> stopps;*/
    @OneToMany(mappedBy="tourId")
    //@JoinColumn(name="tour_id")
    //@NotNull
    private List<Stopp> stopps;


    @Override
    public String toString() {
        return  tour_nr; }

}


