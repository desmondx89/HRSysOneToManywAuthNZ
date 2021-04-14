package com.ntuc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ntuc.model.Semps;

@Repository
public interface SempRepository extends JpaRepository<Semps, Integer> {

	
//	@Query("SELECT p FROM Product p WHERE p.name LIKE %?1%"
//			+ " OR p.brand LIKE %?1%"
//			+ " OR p.madein LIKE %?1%")
//	public List<Product> findAll(String keyword);
	
	@Query("SELECT p FROM Semps, Semps p WHERE "
			+ " CONCAT(p.empid, p.name, p.price, p.sdept, p.details)" 
			+  " LIKE %?1%" )
	public Page<Semps> findAll(String keyword,Pageable pageable);
	//How to include search to semp_details table as well- Ask Simon
}
