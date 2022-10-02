package com.example.pfenningvaadin2022mysql_1.controller;

import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@RequiredArgsConstructor
@RequestMapping("/pfenning2022")
public class TourController {


    private final TourService tourService;

  /*  @GetMapping("tours")
    public List<Tour> findAllTours() {
        return tourService.findAllTours();
    }*/
}