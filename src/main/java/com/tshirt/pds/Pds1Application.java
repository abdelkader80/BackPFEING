package com.tshirt.pds;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.tshirt.pds.entities.Categorie;
import com.tshirt.pds.entities.Produit;
import com.tshirt.pds.repository.CategorieRepository;
import com.tshirt.pds.repository.ProduitRepository;

import net.bytebuddy.utility.RandomString;

@SpringBootApplication
public class Pds1Application {
	/*
	 * @Autowired ProduitRepository produitRepository;
	 * 
	 * @Autowired CategorieRepository categorieRepository;
	 * 
	 * @Autowired private RepositoryRestConfiguration repositoryRestConfiguration;
	 */
	
	

	public static void main(String[] args) {
		SpringApplication.run(Pds1Application.class, args);
	}

	/*
	 * @Override public void run(String... args) throws Exception {
	 * repositoryRestConfiguration.exposeIdsFor(Produit.class,Categorie.class);
	 * categorieRepository.save(new Categorie(1, "Pantallon",null));
	 * categorieRepository.save(new Categorie(2, "t-shirt",null));
	 * categorieRepository.save(new Categorie(3, "Chaussures",null)); Random rnd=new
	 * Random(); categorieRepository.findAll().forEach(c->{ for (int i = 0; i <3;
	 * i++) { Produit p = new Produit(); p.setName(RandomString.make(15));
	 * p.setTaille(RandomString.make(10)); p.setCouleur(RandomString.make(10));
	 * p.setDescription(RandomString.make(35));
	 * p.setCurrentprice(50+rnd.nextInt(200)); p.setPromotion(rnd.nextBoolean());
	 * p.setSelected(rnd.nextBoolean()); p.setDisponible(rnd.nextBoolean());
	 * p.setAvecimage(rnd.nextBoolean()); p.setAvectext(rnd.nextBoolean());
	 * p.getPhotoNom(); p.setCategorie(c); produitRepository.save(p); } });
	 * 
	 * }
	 * 
	 */
}
