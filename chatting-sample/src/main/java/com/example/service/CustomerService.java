package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.CustomerMapper;
import com.example.vo.Customer;

@Service
public class CustomerService {

	@Autowired
	private CustomerMapper customerMapper;
	
	public Customer login(String customerId, String password) {
		Customer customer = customerMapper.getCustomerById(customerId);
		if (customer == null) {
			throw new RuntimeException("고객정보가 존재하지 않습니다.");
		}
		if (!customer.getPassword().equals(password)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
		return customer;
	}
}
