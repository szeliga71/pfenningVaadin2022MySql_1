package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.ArbeitTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArebeitTagRepository extends JpaRepository<ArbeitTag,Long> {

    @Query("select a from ArbeitTag a  where a.arbeitbegin_date > :date ")
   List <ArbeitTag> findArbeitTagesByDate(@Param("date")LocalDate date);


    @Query("select a from ArbeitTag a " +
            "where lower(a.arbeitbegin_date) like lower(concat('%', :dateStart, '%'))")// and lower(concat('%', :dateStop, '%'))")
    //+
      //      "or lower(f.vorname) like lower(concat('%', :searchTerm, '%'))")
    List<ArbeitTag> search(@Param("dateStart") String dateStart);//,@Param("dateStop") String dateStop);
   /*@Query("select at from ArbeitTag at " +
            "where lower(at.arbeitbegin_date) like lower(concat('%', :searchTerm, '%')) ")
        //+
        //      "or lower(f.vorname) like lower(concat('%', :searchTerm, '%'))")
    List<ArbeitTag> search(@Param("searchTerm") String searchTerm);*/

    @Query("select a from ArbeitTag a " +
            "where a.arbeitbegin_date between :dateStart  and :dateStop") //// like lower(concat('%', :dateStart, '%'))")// and lower(concat('%', :dateStop, '%'))")
    List<ArbeitTag> betwe(@Param("dateStart") LocalDate dateStart,@Param("dateStop")LocalDate dateStop);

    //@Query("select f.name from Fahrer f join ArbeitTag a on f.id=a.fahrer_id where a.fahrer_id=f.id")
    //List<Fahrer> fFF();




}
