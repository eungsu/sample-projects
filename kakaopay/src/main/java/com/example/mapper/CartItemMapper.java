package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.vo.CartItem;

@Mapper
public interface CartItemMapper {

	void insertCartItem(CartItem cartItem);
	List<CartItem> getCartItemsByUserNo(int userNo);
	void deleteCartItemByNo(int no);
	void deleteCartItemsByUserNo(int userNo);
}
