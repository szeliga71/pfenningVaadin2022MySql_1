package com.example.pfenningvaadin2022mysql_1.repository;

import com.example.pfenningvaadin2022mysql_1.model.Markt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarktRepository extends JpaRepository<Markt,Long> {
}
