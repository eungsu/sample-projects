package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.kakaopay.ApproveResponse;
import com.example.kakaopay.KakaoPayService;
import com.example.kakaopay.ReadyResponse;
import com.example.service.BookService;
import com.example.service.OrderService;
import com.example.vo.Book;
import com.example.vo.Order;
import com.example.vo.OrderItem;
import com.example.vo.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes({"tid"})
public class OrderController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private KakaoPayService kakaoPayService;
	
	@GetMapping("/form")
	public String form(@RequestParam("no") int bookNo, Model model) {
		Book book = bookService.getBook(bookNo);
		model.addAttribute("book", book);
		
		return "order/form";
	}
	
	@GetMapping("/pay/ready")
	public @ResponseBody ReadyResponse payReady(@RequestParam("no") int bookNo, int quantity, int totalPrice, Model model) {
		log.info("주문 도서번호: " + bookNo);
		log.info("주문 수량: " + quantity);
		log.info("주문 금액: " + totalPrice);
		
		Book book = bookService.getBook(bookNo);
		
		// 카카오 결재 준비 하기
		ReadyResponse readyResponse = kakaoPayService.payReady(book, quantity, totalPrice);
		// 결재고유 번호(tid)를 세션에 저장시킨다.
		model.addAttribute("tid", readyResponse.getTid());
		log.info("결재고유 번호: " + readyResponse.getTid());
		
		return readyResponse;
	}
	
	@GetMapping("/pay/completed")
	public String payCompleted(@RequestParam("pg_token") String pgToken, @ModelAttribute("tid") String tid, Authentication authentication, Model model) {
		User user = (User) authentication.getPrincipal();
		
		log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
		log.info("결재고유 번호: " + tid);
		log.info("로그인한 사용자 정보: " + user);
		
		// 카카오 결재 요청하기
		ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);	
		
		int userNo = user.getNo();
		int totalPrice = approveResponse.getAmount().getTotal();
		int bookNo = Integer.parseInt(approveResponse.getItem_code());	
		int quantity = approveResponse.getQuantity();
		
		OrderItem orderItem = new OrderItem();
		orderItem.setBookNo(bookNo);
		orderItem.setQuantity(quantity);
		
		orderService.saveOrder(userNo, tid, totalPrice, orderItem);
		
		return "redirect:/order/completed?id=" + tid;
	}
	
	@RequestMapping("/completed")
	public String completed(@RequestParam("id") String orderId, Model model) {
		
		Order order = orderService.getOrderById(orderId);
		model.addAttribute("order", order);
		
		return "order/completed";
	}

}
