package com.example.processPension.reository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.processPension.entity.PensionDetail;

@Repository
public interface ProcessPensionRepository extends JpaRepository<PensionDetail, Integer>{

}
