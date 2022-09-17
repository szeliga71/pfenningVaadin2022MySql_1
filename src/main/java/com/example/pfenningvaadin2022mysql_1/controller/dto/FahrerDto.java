package com.example.pfenningvaadin2022mysql_1.controller.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class FahrerDto {

        private long id;
        private String id_pf;
        private String id_rewe;
        private String name;
        private String vorname;
        private String sprache;


}
