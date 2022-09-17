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

    public List<ArbeitTag>findAllArbeitTaggs(){
        return arbeitTagRepository.findAll();
    }




    public List<ArbeitTag> findArbeitTagesByDate(LocalDate dateStart,LocalDate dateStop) {



     /*   if (dateStart==null   ||  dateStop==null ){
        //((dateStart == null || dateStart.isEmpty)  &&  (dateStop == null || dateStop.isEmpty())) {
            return arbeitTagRepository.findAll();
        } else {*/
            return arbeitTagRepository.betwe(dateStart,dateStop);

        //return arbeitTagRepository.findAll();
        //}
    }
    public void addArbeitTag(ArbeitTag arbeitTag) {
        if (arbeitTag == null) {
            System.err.println("Fahrer is null. Are you sure you have connected your form to the application?");
            return;
        }
        arbeitTagRepository.save(arbeitTag);

    }

    public void deleteArbeitTag(ArbeitTag arbeitTag) {
        arbeitTagRepository.delete(arbeitTag);

    }

   /* public List<ArbeitTag> fFF(Long fahrer_id){
           if (name == null || name.isEmpty()) {
        return arbeitTagRepository.findAll();
    } else {
       // return arbeitTagRepository.search(name);

        return arbeitTagRepository.findById(fahrer_id);
    }
    }*/

   /* public List<ArbeitTag> findAllArbeitTagesByFaherName(String name,String dateStart,String dateStop) {
        if(name.equals("Zachariew")){
            return arbeitTagRepository.findAllArbeitTagesByFaherName(1);
        }
        else
            return arbeitTagRepository.betwe(dateStart,dateStop);
    }*/



    /*public List<ArbeitTag> findAllArbeitTagesByDate(LocalDateTime data) {
        return arbeitTagRepository.findArbeitTagesByDate(data);
    }*/
}
