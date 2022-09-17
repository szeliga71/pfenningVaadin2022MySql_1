package com.example.pfenningvaadin2022mysql_1.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class StoppDto {

    private long id;
    private LocalDateTime ankunftstopp;
    private LocalDateTime abfahrtstopp;
    private String leergut;
    private long id_markt;
    private long tour_id;
}
