package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.Customer;

@Mapper
public interface CustomerMapper {

	Customer getCustomerById(String customerId);
}
