package com.tshirt.pds.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @AllArgsConstructor @NoArgsConstructor @ToString @Data
public class Produit implements Serializable {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, nullable = false)
	private String name;
	private String taille;
	private String couleur;
	private String Description;
	private double currentprice;
	private boolean promotion;
	private boolean selected;
	private boolean disponible;
	private boolean avecimage;
	private boolean avectext;
	public String photoNom="unknown.png";
	@Transient
	private int quantite=1;
	@ManyToOne
	private Categorie categorie;
	
	
}
