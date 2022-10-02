package com.example.pfenningvaadin2022mysql_1.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lkw {
    @Override
    public String toString() {
        return kenz ;
    }

    @Id
    private String kenz;
    private String nr_rewe;
    private String marke;
}
