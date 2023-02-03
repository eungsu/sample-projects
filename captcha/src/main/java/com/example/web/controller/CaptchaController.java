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

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private CaptchService captchService;
	
	@GetMapping("/key")
	public ResponseEntity<String> captchaKey() throws IOException {
		return ResponseEntity.ok(captchService.captchaKey());
	}
	
	@GetMapping("/image")
	public ResponseEntity<ByteArrayResource> captchaImage(@RequestParam("key") String key) throws IOException {
		byte[] bytes = captchService.captchaImage(key);
		
		ByteArrayResource resource = new ByteArrayResource(bytes);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestParam("key") String key, 
			@RequestParam("value") String value) throws IOException {
		return ResponseEntity.ok()
				.body(captchService.checkCaptcha(key, value));
	}
	
}
