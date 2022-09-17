package com.example.pfenningvaadin2022mysql_1.controller.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TourDto {

    private long id;
    private LocalDateTime abfahrtlager;
    private LocalDateTime ankunftlager;
    private long arbeit_tag_id;
    private String lkw_kenz;
    private int tour_kilometer;
    private String tour_nr;
}
