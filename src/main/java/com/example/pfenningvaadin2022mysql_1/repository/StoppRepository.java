package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Stopp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoppRepository extends JpaRepository<Stopp, Long> {


    @Query("select s from Stopp s " +
            "where lower(s.marktId) like lower(concat('%', :marktId, '%')) ")
            //"or lower(f.vorname) like lower(concat('%', :searchTerm, '%'))")
    List<Stopp> search(@Param("marktId") String searchTerm);


}
