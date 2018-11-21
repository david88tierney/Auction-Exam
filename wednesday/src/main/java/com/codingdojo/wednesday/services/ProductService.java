package com.codingdojo.wednesday.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.codingdojo.wednesday.models.Product;
import com.codingdojo.wednesday.repositories.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository pR;
	
	public ProductService(ProductRepository pR) {
		this.pR= pR;
	}
	
	public Product create(Product product) {return pR.save(product);}
	
	public ArrayList<Product> findAll(){return (ArrayList<Product>) pR.findAll();}
	
	public Product findById(Long id) {return pR.findById(id).get();}
	
	public Product update(Product product) { return pR.save(product);}
	
	public void destroy(Long id) { pR.deleteById(id);}
	
	public Product findName(String name) { return pR.findByName(name);}
	
	

}
