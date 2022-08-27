package com.example.pfenningvaadin2022mysql_1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime abfahrtlager;
    private LocalDateTime ankunftlager;
    private long arbeit_tag_id;
    private String lkw_kenz;
    private int tour_kilometer;
    private String tour_nr;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name="tour_id",updatable = false,insertable = false)
    private List<Stopp> stopps;


    }


