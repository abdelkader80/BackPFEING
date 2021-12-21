package com.tshirt.pds.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.tshirt.pds.entities.Produit;

@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer  {
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Produit.class);
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config);
	}
}