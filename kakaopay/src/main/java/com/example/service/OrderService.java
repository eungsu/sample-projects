package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.BookMapper;
import com.example.mapper.OrderItemMapper;
import com.example.mapper.OrderMapper;
import com.example.mapper.UserMapper;
import com.example.vo.Book;
import com.example.vo.Order;
import com.example.vo.OrderItem;
import com.example.vo.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	
	public void saveOrder(int userNo, String tid, int totalPrice, OrderItem orderItem) {
		log.info("주문한 사용자 번호: " + userNo);
		log.info("결재고유 번호: " + tid);
		log.info("총 구매금액: " + totalPrice);
		log.info("주문 아이템 정보: " + orderItem);
		
		// 주문정보 객체 생성
		Order order = new Order();
		order.setId(tid);
		order.setUserNo(userNo);
		order.setOrderStatus("결재완료");
		order.setTotalPrice(totalPrice);
		// 주문정보를 테이블에 저장시키기
		orderMapper.insertOrder(order);
		log.info("저장된 주문정보: " + order);
		
		// 책 정보 조회, 주문상품정보에 주문번호, 해당상품의 구매금액 저장
		Book book = bookMapper.getBookByNo(orderItem.getBookNo());
		int price = book.getPrice();
		int quantity = orderItem.getQuantity();
		int orderItemPrice = price * quantity;
		orderItem.setPrice(orderItemPrice);
		orderItem.setOrderId(order.getId());
		// 주문상품 정보를 테이블에 저장시키기
		orderItemMapper.insertOrderItem(orderItem);
		
		// 적립포인트 계산
		int point = (int) (orderItemPrice*0.05);
		User user = userMapper.getUserByNo(userNo);
		user.setPoint(user.getPoint() + point);
		// 변경된 사용자의 적립포인트를 테이블에 반영시키기
		userMapper.updateUser(user);
		
		// 도서 재고량 변경
		book.setStock(book.getStock() - quantity);
		// 변경된 도서 재고량을 테이블에 반영시키기
		bookMapper.updateBook(book);		
	}
	
	public Order getOrderById(String orderId) {
		return orderMapper.getOrderById(orderId);
	}
	
	
}
