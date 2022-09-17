package com.example.pfenningvaadin2022mysql_1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.helger.commons.name.IHasName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ArbeitTag  {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;


    @ManyToOne
    @JoinColumn(name = "fahrer_id",updatable = false,insertable = false)
    @NotNull
    @JsonIgnoreProperties({"arbeitTages"})
    private Fahrer fahrer_name;

    private long fahrer_id;


    private LocalDate arbeitbegin_date;

    private String arbeitbegin_zeit;
    private String arbeitende_zeit;

    private int kilometer;
    private int kilometer_rewe;
    private String fahrerbruch;
    private String unfall;
    private String pause;


  @OneToMany  (cascade = CascadeType.ALL)
    @JoinColumn(name="arbeit_tag_id",updatable = false,insertable = false)
    private List<Tour> touren;



}
