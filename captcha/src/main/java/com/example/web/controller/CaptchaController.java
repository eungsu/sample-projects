package com.example.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.CaptchService;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

	@Autowired
	private CaptchService captchService;
	
	@GetMapping("/image")
	public ResponseEntity<ByteArrayResource> captchaImage() throws IOException {
		byte[] bytes = captchService.captchaImage();
		
		ByteArrayResource resource = new ByteArrayResource(bytes);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
	}
	
}
