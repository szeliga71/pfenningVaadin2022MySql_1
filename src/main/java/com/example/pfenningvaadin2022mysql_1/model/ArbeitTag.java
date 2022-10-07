package com.example.pfenningvaadin2022mysql_1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helger.commons.name.IHasName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ArbeitTag  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;


   @ManyToOne//(fetch = FetchType.LAZY)
   @JoinColumn(name = "fahrer.name")//,updatable = false,insertable = false)
    @NotNull
    @JsonIgnoreProperties({"arbeitTages"})
    private Fahrer fahrer_name;
    /*@JoinTable(name="fahrer")
    @NotNull
   private String fahrer_name;*/


    private LocalDate arbeitbegin_date;

    private LocalTime arbeitbegin_zeit;
    private LocalTime arbeitende_zeit;

    private int kilometer;

    private String fahrerbruch;
    private String unfall;
    private String pause;


 /* @OneToMany  (mappedBy="arbeit_tag_id")
  //@JoinColumn(name="arbeit_tag_id",updatable = false,insertable = false)
    private List<Tour> touren;*/

    @OneToMany
    @Nullable
    @JoinColumn(name="arbeit_tag_id")
    private List<Tour> touren;


    @Override
    public String toString() {
        return  ""+Id;
    }
}
