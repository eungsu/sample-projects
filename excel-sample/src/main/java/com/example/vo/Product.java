package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Product {

	private int no;
	private String name;
	private String maker;
	private int price;
	private int discountPrice;
	private int stock;
	private Date createdDate;
	private Date updateDate;
}
