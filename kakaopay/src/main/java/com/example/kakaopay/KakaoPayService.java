package com.example.kakaopay;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.example.vo.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KakaoPayService {

	public ReadyResponse payReady(Book book, int quantity, int totalPrice) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("cid", "TC0ONETIME");
		parameters.add("partner_order_id", "1234567890");
		parameters.add("partner_user_id", "북스토어");
		parameters.add("item_name", book.getTitle());
		parameters.add("item_code", String.valueOf(book.getNo()));
		parameters.add("quantity", String.valueOf(quantity));
		parameters.add("total_amount", String.valueOf(totalPrice));
		parameters.add("tax_free_amount", "0");
		parameters.add("approval_url", "http://localhost/order/pay/completed");
		parameters.add("cancel_url", "http://localhost/order/pay/cancel");
		parameters.add("fail_url", "http://localhost/order/pay/fail");
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		
		RestTemplate template = new RestTemplate();
		String url = "https://kapi.kakao.com/v1/payment/ready";
		ReadyResponse readyResponse = template.postForObject(url, requestEntity, ReadyResponse.class);
		log.info("결재준비 응답객체: " + readyResponse);
		
		return readyResponse;
	}
	
	public ApproveResponse payApprove(String tid, String pgToken) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
		parameters.add("cid", "TC0ONETIME");
		parameters.add("tid", tid);
		parameters.add("partner_order_id", "1234567890");
		parameters.add("partner_user_id", "북스토어");
		parameters.add("pg_token", pgToken);
		
		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		
		RestTemplate template = new RestTemplate();
		String url = "https://kapi.kakao.com/v1/payment/approve";
		ApproveResponse approveResponse = template.postForObject(url, requestEntity, ApproveResponse.class);
		log.info("결재승인 응답객체: " + approveResponse);
		
		return approveResponse;
	}
	
	private HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "KakaoAK 카카오AdminKey");
		headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		return headers;
	}
}
