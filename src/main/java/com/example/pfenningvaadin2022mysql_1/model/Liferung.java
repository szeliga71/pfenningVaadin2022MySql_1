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
public class Liferung {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private long ware_id;
    private long stopp_id;
    private int menge;

}