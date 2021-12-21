
package com.tshirt.pds.controleur;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tshirt.pds.entities.Categorie;
import com.tshirt.pds.entities.Produit;
import com.tshirt.pds.repository.ProduitRepository;
import com.tshirt.pds.service.ProduitService;

import ch.qos.logback.core.net.SyslogOutputStream;
@CrossOrigin("*")
@RestController
@RequestMapping("/app")
public class catalogueRestController {
	private ProduitRepository produitRepository;
	
	@Autowired
	private ProduitService produitService;
	

	public catalogueRestController(ProduitRepository produitRepository) {
		this.produitRepository = produitRepository;
	}
	 @GetMapping(path="/photoProduct/{id}", produces= MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Produit p=produitRepository.findById(id).get();
		 return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerceapp/produit/"+p.getPhotoNom()));
		 
		 
		
	}
	 @PostMapping(path="/uploadphoto/{id}")
	 public void uploadphoto(@RequestParam("file") MultipartFile file, @PathVariable Long id) throws Exception {
		 Produit p=new Produit();
		
		 p=produitRepository.findById(id).get();
		 //System.out.println(p);
		 System.out.println(file);
		 p.setPhotoNom(id+".png");
     	 System.out.println(System.getProperty("user.home"));
		 Files.write(Paths.get(System.getProperty("user.home")+"/ecommerceapp/produit/"+p.getPhotoNom()), file.getBytes());
		 produitRepository.save(p);
	 }
	 
	 @GetMapping("/listproduit")
	  public List<Produit> getproduit(){
		  return produitService.getallProduits();
	  }
	 @GetMapping("/listcat")
	  public List<Categorie> getcat(){
		 System.out.println("Controleur Activé");
		  return produitService.getallcat();
	  }
	
	 @PostMapping("/addproduit")
		private Produit addproduit(@RequestBody Produit produit) {
			return produitService.addproduit(produit);
			
	  }
	 @DeleteMapping("/listproduit/{id}")
	  public void deleteproduit(@PathVariable Long id) {
		  produitService.deleteProduitById(id);
	  }
	 @PutMapping("/empl/{id}")
     public Produit  update(@PathVariable Long id, @RequestBody Produit prod) {
    	 Produit produit = produitRepository.findById(id).orElse(null);
    	 produit.setName(prod.getName());
    	 produit.setTaille(prod.getTaille());
    	 produit.setCouleur(prod.getCouleur());
    	 produit.setDescription(prod.getDescription());
    	 produit.setCurrentprice(prod.getCurrentprice());
    	 produit.setQuantite(prod.getQuantite());
    	 produit.setPromotion(prod.isPromotion());
    	 produit.setSelected(prod.isSelected());
    	 
    	
    	 Produit  updatprod = produitRepository.save(produit);
    	 return updatprod;
     }
	 

}
