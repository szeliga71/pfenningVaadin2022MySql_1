package com.example.pfenningvaadin2022mysql_1.service;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.repository.FahrerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FahrerService {

    private final FahrerRepository fahrerRepository;


    public List<Fahrer> getAllFahrers() {
        return fahrerRepository.findAll();
    }

    public Fahrer getFahrerById(long id) {
        return fahrerRepository.findById(id).orElseThrow();
    }

    public List<Fahrer> getFahrerByName(String name) {

        return fahrerRepository.findByName(name);
    }

    public Fahrer addFahrer(Fahrer fahrer) {

        return fahrerRepository.save(fahrer);

    }

   public List<Fahrer> getFahrerByIdPf(String id_pf) {

       return fahrerRepository.findByid_pf(id_pf);
    }

    public void deleteFahrerById(long id) {
        fahrerRepository.deleteById(id);

    }
}
