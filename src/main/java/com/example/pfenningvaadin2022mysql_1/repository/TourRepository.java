package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.Fahrer;
import com.example.pfenningvaadin2022mysql_1.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {

    @Query("select t from Tour t " +
            "where lower(t.lkw_kenz) like lower(concat('%', :lkw, '%')) ")
            //"or lower(f.vorname) like lower(concat('%', :lke, '%'))")
    List<Tour> searchLkw(@Param("lkw") String lkw);
@Query("select t from Tour t where t.arbeit_tag_id=:id" )
    List<Tour> findByAT(@Param("id") long id);
@Query("select t from Tour t where lower(t.tour_nr)like lower (concat('%', :tour_nr,'%'))")
    List<Tour> searchByTourNr(@Param("tour_nr") String stringFilter);
}
