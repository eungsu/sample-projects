package com.example.service;

import java.io.IOException;
import java.util.Map;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CaptchService {

	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String secret;
	
	@Value("${naver.captcha.url.key}")
	private String captchaKeyUrl;
	
	@Value("${naver.captcha.url.image}")
	private String captchaImageUrl;
	
	@Value("${naver.captcha.url.validation}")
	private String captchaValidationUrl;
	
	public byte[] captchaImage() throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			return httpClient.execute(httpRequest(captchaImageUrl, captchaKey()), response -> {
				return EntityUtils.toByteArray(response.getEntity());
			});
		}
	}
	
	public boolean checkCaptcha() {
		return false;
	}
	
	private String captchaKey() throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			return httpClient.execute(httpRequest(captchaKeyUrl), response -> {
				return jsonToMap(EntityUtils.toString(response.getEntity())).get("key");
			});
		}
	}
	
	private ClassicHttpRequest httpRequest(String uri, String captchaKey, String value) {
		return requestBuilder(uri)
				.addParameter("key", captchaKey)
				.addParameter("value", value)
				.build();
	}
	
	private ClassicHttpRequest httpRequest(String uri, String captchaKey) {
		return requestBuilder(uri)
				.addParameter("key", captchaKey)
				.build();
	}
	
	private ClassicHttpRequest httpRequest(String uri) {
		return requestBuilder(uri)
				.build();
	}
	
	private ClassicRequestBuilder requestBuilder(String uri) {
		return ClassicRequestBuilder.get()
				.setUri(uri)
				.addHeader("X-Naver-Client-Id", clientId)
				.addHeader("X-Naver-Client-Secret", secret);
		
	}
	
	private Map<String, String> jsonToMap(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
	}
}
