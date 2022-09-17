package com.example.pfenningvaadin2022mysql_1.service;

import com.example.pfenningvaadin2022mysql_1.model.Markt;
import com.example.pfenningvaadin2022mysql_1.repository.MarktRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarktService {

    private final MarktRepository marktRepository;
    public List<Markt> getAllMarkt(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return marktRepository.findAll();
        } else {
            return marktRepository.search(stringFilter);
        }
    }



    public void addMarkt(Markt markt) {
        marktRepository.save(markt);
    }


    public void deleteMarkt(Markt markt) {
        marktRepository.delete(markt);

    }
}
