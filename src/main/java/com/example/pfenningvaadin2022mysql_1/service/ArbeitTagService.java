package com.example.pfenningvaadin2022mysql_1.service;


import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.repository.ArebeitTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArbeitTagService {

    private final ArebeitTagRepository arebeitTagRepository;

    public List<ArbeitTag>findAllArbeitTages(){
        return arebeitTagRepository.findAll();
    }

}
