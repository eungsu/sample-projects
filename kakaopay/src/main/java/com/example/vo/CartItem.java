package com.example.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartItem {

	private int no;
	private int userNo;
	private int bookNo;
	private int quantity;
}
