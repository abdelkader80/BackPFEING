package com.tshirt.pds.service;

import java.util.List;

import com.tshirt.pds.entities.Categorie;
import com.tshirt.pds.entities.Produit;




public interface ProduitService {
	
    List<Produit> getallProduits();
    List<Categorie> getallcat();
	Produit addproduit(Produit produit);
	Categorie addcat(Categorie categorie);
	Produit updateproduit(Produit produit);
	Produit getproduit(long id);
	void deleteProduitById(Long id);
	public Categorie getcatid(Long id);
}
