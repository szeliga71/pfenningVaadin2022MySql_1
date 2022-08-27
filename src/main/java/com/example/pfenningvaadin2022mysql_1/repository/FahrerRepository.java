package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FahrerRepository extends JpaRepository<Fahrer,Long>{

@Query("select f from Fahrer f where name = ?1")
   List<Fahrer> findByName(String name);

   @Query("select f from Fahrer f where id_pf = ?1")
   List<Fahrer> findByid_pf(String id_pf);





}
