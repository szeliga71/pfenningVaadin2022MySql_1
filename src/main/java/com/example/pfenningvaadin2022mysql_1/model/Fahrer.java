package com.example.pfenningvaadin2022mysql_1.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Fahrer {

    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private String name;
    private String vorname;
    private String id_pf;
    private String id_rewe;
    private String sprache;



    @OneToMany(mappedBy="fahrer_name")  //  (cascade = CascadeType.ALL)
    //@JoinColumn(name="arbeit_tag.fahrer_name",updatable = false,insertable = false)
    private List<ArbeitTag>arbeitTages;
    /*@OneToMany
    @Nullable
    @JoinColumn(name="fahrer_name")
    private List<ArbeitTag>arbeitTages;*/
    @Override
    public String toString() {
        return name ;
    }
}
