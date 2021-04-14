package com.ntuc.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ntuc.model.Users;

public interface UserRepository extends CrudRepository<Users, Long> {

	@Query("SELECT u FROM Users u WHERE u.username = :username")
	public Users getUserByUsername(@Param("username") String username);
}
