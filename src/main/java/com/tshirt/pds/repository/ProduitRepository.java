package com.tshirt.pds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.tshirt.pds.entities.Categorie;
import com.tshirt.pds.entities.Produit;

@CrossOrigin("*")
@RepositoryRestResource
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	@RestResource(path = "/produitenselection")
	List<Produit> findBySelectedIsTrue();
	@RestResource(path = "/rechercheparmotcle")
	List<Produit> findByNameContains(@Param("mc") String mc);
	@RestResource(path = "/produitenpromo")
	List<Produit> findByPromotionIsTrue();
	@RestResource(path = "/produitdispo")
	List<Produit> findByDisponibleIsTrue();
	@RestResource(path="/productbycat")
	List<Produit> findByCategorie_Id(@RequestParam Long id);
	

}
