package com.ntuc.repository;

import com.ntuc.model.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeptRepository extends JpaRepository<Sdept, Integer> {

}
