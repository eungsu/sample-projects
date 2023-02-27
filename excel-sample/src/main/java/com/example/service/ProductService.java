package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.ProductMapper;
import com.example.vo.Product;

@Service
public class ProductService {

	@Autowired
	private ProductMapper productMapper;
	
	public void insertProducts(List<Map<String, Object>> datas) {
		productMapper.insertProducts(datas);
	}
	
	public List<Product> getAllProducts() {
		return productMapper.getAllProducts();
	}
	
	public List<Map<String, Object>> getProducts() {
		return productMapper.getAllProductsForMap();
	}
}
