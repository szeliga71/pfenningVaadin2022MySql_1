package com.example.pfenningvaadin2022mysql_1.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ArbeitTag {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    
    private long fahrer_id;
    private LocalDateTime arbeitbegin;
    private LocalDateTime arbeitende;
    private int kilometer;
    private int kilometer_rewe;
    private String fahrerbruch;
    private String unfall;
    private String pause;


  @OneToMany   //(cascade = CascadeType.REFRESH)
    @JoinColumn(name="arbeit_tag_id",updatable = false,insertable = false)
    private List<Tour> touren;


}
