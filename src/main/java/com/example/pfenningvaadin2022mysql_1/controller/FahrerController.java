package com.example.pfenningvaadin2022mysql_1.controller;


import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.service.FahrerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pfenning2022")
public class FahrerController {

    private final FahrerService fahrerService;


    @GetMapping("")
    public List<Fahrer> getAllFahrer() {
        return fahrerService.getAllFahrer();
    }

   /* @GetMapping("/name/{name}")
    public List<Fahrer> getFahrerByName(@PathVariable String name) {
        return fahrerService.getFahrerByName(name);
    }*/

    @GetMapping("{id}")
    public Fahrer getFahrerByIdPf(@PathVariable long id) {
        return fahrerService.getFahrerById(id);

    }

/*@PostMapping("add")
    public Fahrer addFahrer(@RequestBody Fahrer fahrer){
       return fahrerService.addFahrer(fahrer);
    }*/


   /*@GetMapping("/id_pf/{id_pf}")
    public List<Fahrer> getFahrerByIdPf(@PathVariable String id_pf) {
        return fahrerService.getFahrerByIdPf(id_pf);

    }*/

    @DeleteMapping("deleteFahrer/{id}")
    public void deleteFahrerById(@PathVariable long id){
        fahrerService.deleteFahrerById(id);
    }

}
