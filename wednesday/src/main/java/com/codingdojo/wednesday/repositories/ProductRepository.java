package com.codingdojo.wednesday.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.wednesday.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
	Product findByName(String name);
}
