package com.example.pfenningvaadin2022mysql_1.service;


import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import com.example.pfenningvaadin2022mysql_1.repository.ArebeitTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArbeitTagService {

    private final ArebeitTagRepository arbeitTagRepository;

    public List<ArbeitTag> findAllArbeitTaggs() {
        return arbeitTagRepository.findAll();
    }


    public List<ArbeitTag> findArbeitTagesByDate(LocalDate dateStart, LocalDate dateStop, String name) {

        if (name == null || name.isEmpty()) {
            return arbeitTagRepository.betwe(dateStart, dateStop);
        } else {

            return arbeitTagRepository.betweName(dateStart, dateStop, name);

        }
    }

    public List<ArbeitTag> findArbeitTagesByName(LocalDate dateStart, LocalDate dateStop, String name) {

        if (name == null || name.isEmpty()) {

            return arbeitTagRepository.betwe(dateStart, dateStop);
        } else {

            return arbeitTagRepository.betweName(dateStart, dateStop, name);
        }
    }


    public void addArbeitTag(ArbeitTag arbeitTag) {
        if (arbeitTag == null) {
            System.err.println("Arbeit Tag is null. Are you sure you have connected your form to the application?");
            return;
        }
        arbeitTagRepository.save(arbeitTag);

    }

    public void deleteArbeitTag(ArbeitTag arbeitTag) {
        arbeitTagRepository.delete(arbeitTag);

    }



    public ArbeitTag getArbeitTagById(long id) {

       return arbeitTagRepository.
               findById(id).orElseThrow();
    }

    public List<Long> getAllIds() {
        return arbeitTagRepository.getAllIds();
    }
}

