package com.tshirt.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tshirt.pds.entities.Role;
import com.tshirt.pds.entities.User;

@CrossOrigin("*")
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
	public User findByLogin(String login);

}