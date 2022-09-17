package com.example.pfenningvaadin2022mysql_1.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ArbeitTagDto {

    private long Id;
    private long fahrer_id;
    private LocalDateTime arbeitbegin;
    private LocalDateTime arbeitende;
    private int kilometer;
    private int kilometer_rewe;
    private String fahrerbruch;
    private String unfall;
    private String pause;
}
