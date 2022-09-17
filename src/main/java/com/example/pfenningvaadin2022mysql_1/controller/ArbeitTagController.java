package com.example.pfenningvaadin2022mysql_1.controller;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.service.ArbeitTagService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pfenning2022")
public class ArbeitTagController {

    private final ArbeitTagService arbeitTagService;


   /* @GetMapping("arbeitTages")
    public List<ArbeitTag>getAllArbeitTages(){
        return arbeitTagService.findAllArbeitTages();
    }*/

   // @GetMapping("fahrerName")
    //public List<Fahrer>fFF(){
       // return arbeitTagService.fFF();
    }



