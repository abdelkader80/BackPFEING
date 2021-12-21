package com.tshirt.pds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tshirt.pds.entities.Categorie;
import com.tshirt.pds.entities.Produit;
import com.tshirt.pds.repository.CategorieRepository;
import com.tshirt.pds.repository.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService{
	
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	CategorieRepository categorieRepository;

	@Override
	public List<Produit> getallProduits() {
		
		return produitRepository.findAll();
	}
	@Override
	public List<Categorie> getallcat() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Produit addproduit(Produit produit) {
		return produitRepository.save(produit);
		 
	}

	@Override
	public Produit updateproduit(Produit produit) {
		return produitRepository.save(produit);
	}

	public Produit getproduit(long id) {
		return produitRepository.findById(id).get();
	}

	public void deleteProduitById(Long id) {
		produitRepository.deleteById(id);
		
	}


}
