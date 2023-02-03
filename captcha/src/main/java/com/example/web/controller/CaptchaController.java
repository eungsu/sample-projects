package com.example.web.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.CaptchService;
 
/**
 * 캡차 관련 요청을 처리하는 컨트롤러다.
 * 
 * <p> 캡차 이미지 발급에 필요한 캡차 이미지 키값을 제공하는 요청핸들러 메소드를 제공한다.
 * <p> 캡차 이미지(바이너리 데이터)를 제공하는 요청핸들러 메소드를 제공한다.
 * <p> 사용자가 입력한 값을 검증하는 요청핸들러 메소드를 제공한다.
 *
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private CaptchService captchService;
	
	/**
	 * 캡차 이미지 요청에 필요한 일회용 캡차 이미지 키정보를 json으로 보낸다.
	 * @return 캡차 이미지 키 정보, {key:xxxxxx}
	 * @throws IOException
	 */
	@GetMapping("/key")
	public ResponseEntity<Map<String, String>> captchaKey() throws IOException {
		return ResponseEntity.ok(captchService.captchaKey());
	}
	
	/**
	 * 캡차 이미지(바이너리 데이터)를 응답으로 보낸다.
	 * 
	 * <p> 캡차 이미지를 요청하기 위해서는 일회용 캡차 이미지 키값이 필요한다.
	 * @param key 캡차 이미지 요청에 필요한 일회용 캡차 이미지 키값
	 * @return 캡차 이미지(바이너리 데이터)
	 * @throws IOException
	 */
	@GetMapping("/image")
	public ResponseEntity<ByteArrayResource> captchaImage(@RequestParam("key") String key) throws IOException {
		byte[] bytes = captchService.captchaImage(key);
		
		ByteArrayResource resource = new ByteArrayResource(bytes);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
	}
	
	/**
	 * 사용자가 입력한 캡차 이미지 값을 검증하고, 그 결과를 json으로 반환한다.
	 * @param key 캡차 이미지 키 값
	 * @param value 사용자자가 입력한 캡차 이미지 입력값
	 * @return 사용자가 입력한 입력값을 검증한 결과, {result:불린값, responseTime:소요시간}
	 * @throws IOException
	 */
	@PostMapping("/confirm")
	public ResponseEntity<Map<String, Object>> register(@RequestParam("key") String key, 
			@RequestParam("value") String value) throws IOException {
		return ResponseEntity.ok()
				.body(captchService.checkCaptcha(key, value));
	}
	
}
