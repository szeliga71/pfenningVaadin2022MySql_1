package com.example.pfenningvaadin2022mysql_1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Fahrer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String id_pf;
    private String id_rewe;
    private String name;
    private String vorname;
    private String sprache;



    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="fahrer_id",updatable = false,insertable = false)
    private List<ArbeitTag>arbeitTages;

    @Override
    public String toString() {
        return name ;
    }
}
