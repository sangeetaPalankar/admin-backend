package com.capstone.application.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.capstone.application.model.VisitDetails;

public interface PatietHealthRecordsRepository extends JpaRepository<VisitDetails,Integer> {

}
