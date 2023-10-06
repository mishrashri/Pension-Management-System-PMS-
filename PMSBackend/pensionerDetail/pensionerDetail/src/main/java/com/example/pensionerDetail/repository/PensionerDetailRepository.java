package com.example.pensionerDetail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pensionerDetail.entity.PensionerDetail;

@Repository
public interface PensionerDetailRepository extends JpaRepository<PensionerDetail, Long>{
	
	@Query(value = "select P from PensionerDetail P where P.aadhaar = ?1")
	public PensionerDetail getPensionerDetail(long aadhaar);
}
