package com.example.pfenningvaadin2022mysql_1.service;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Stopp;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import com.example.pfenningvaadin2022mysql_1.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService {

    private final TourRepository tourRepository;

    public List<Tour> findAllTours(String tour){
        return tourRepository.findAll();
    }

    public List<Tour> findAllTourr(){return tourRepository.findAll();}

    public void deleteTour(Tour tour) {
        tourRepository.delete(tour);

    }
    public void addTour(Tour tour) {
        if (tour == null) {
            System.err.println("Tour is null. Are you sure you have connected your form to the application?");
            return;
        }
        tourRepository.save(tour);

    }

public List<Tour>showTourByAT(long id){
        return tourRepository.findByAT(id);
}

    public List<Tour> findAllToursByTourNr(String stringFilter) {

        if (stringFilter == null || stringFilter.isEmpty()) {
            return tourRepository.findAll();
        } else {
            return tourRepository.searchByTourNr(stringFilter);
        }
    }

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }
}
