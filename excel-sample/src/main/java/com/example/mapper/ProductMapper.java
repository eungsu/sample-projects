package com.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.vo.Product;

@Mapper
public interface ProductMapper {

	List<Product> getAllProducts();
	List<Map<String, Object>> getAllProductsForMap();
	void insertProducts(@Param("products") List<Map<String, Object>> products);
}
