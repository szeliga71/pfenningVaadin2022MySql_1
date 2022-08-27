package com.example.pfenningvaadin2022mysql_1.service;

import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

    public List<Tour> findAllTours(){
        return tourRepository.findAll();
    }

}
