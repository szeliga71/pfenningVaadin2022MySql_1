package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FahrerRepository extends JpaRepository<Fahrer,String>{


   @Query("select f from Fahrer f " +
           "where lower(f.name) like lower(concat('%', :searchTerm, '%')) " +
           "or lower(f.vorname) like lower(concat('%', :searchTerm, '%'))")
   List<Fahrer> search(@Param("searchTerm") String searchTerm);

 /*@Query("Delete from Fahrer f where f.name = :name")
   void deleteById(@Param("name")String name);*/

  /* @Query("select f from Fahrer f")
List<Fahrer>findAll();*/



}
