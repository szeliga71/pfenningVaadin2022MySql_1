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


    public List<Fahrer> getAllFahrer() {
        return fahrerRepository.findAll();
    }

    public List<Fahrer> getAllFahrers(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return fahrerRepository.findAll();
        } else {
            return fahrerRepository.search(stringFilter);
        }
    }
    public Fahrer getFahrerById(long id) {
        return fahrerRepository.findById(id).orElseThrow(); //OPTIONAL
    }

   /* public List<Fahrer> getFahrerByName(String name) {

        return fahrerRepository.findByName(name);
    }*/

    public void addFahrer(Fahrer fahrer) {
        if (fahrer == null) {
            System.err.println("Fahrer is null. Are you sure you have connected your form to the application?");
            return;
        }
        fahrerRepository.save(fahrer);

    }

   /*public List<Fahrer> getFahrerByIdPf(String id_pf) {

       return fahrerRepository.findByid_pf(id_pf);
    }*/

    public void deleteFahrerById(long id) {
        fahrerRepository.deleteById(id);

    }

    public void deleteFahrer(Fahrer fahrer) {
        fahrerRepository.delete(fahrer);

    }
}
