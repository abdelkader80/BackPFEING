package com.tshirt.pds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.tshirt.pds.entities.Categorie;

@CrossOrigin("*")
@RepositoryRestResource
public interface CategorieRepository extends JpaRepository<Categorie, Long>{


}
