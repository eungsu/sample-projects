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

/**
 * 캡차관련 서비스 클래스다.
 *
 * <p>캡차이미지 요청에 사용되는 일회용 키를 제공한다. 
 * 키는 캡차이미지를 요청할 때, 캡차이미지에 대한 입력값을 검증할 때 사용한다.
 * 
 * <p>캡차이미지에 대한 입력값을 검증하고, 그 결과를 제공한다.
 */
@Service
public class CaptchService {

	/**
	 * 네이버 애플리케이션 클라이언트 아이디
	 */
	@Value("${naver.client.id}")
	private String clientId;
	
	/**
	 * 네이버 애플리케이션 클라이언트 시크릿
	 */
	@Value("${naver.client.secret}")
	private String secret;
	
	/**
	 * 캡차 키 발급요청 URL
	 */
	@Value("${naver.captcha.url.key}")
	private String captchaKeyUrl;
	
	/**
	 * 캡차 이미지 요청 URL
	 */
	@Value("${naver.captcha.url.image}")
	private String captchaImageUrl;
	
	/**
	 * 캡차 입력값 검증 요청 URL
	 */
	@Value("${naver.captcha.url.validation}")
	private String captchaValidationUrl;
	
	/**
	 * 캡차 이미지 요청 및 캡차 이미지 입력값 검증에 활용되는 일회용 캡차 키를 제공한다.
	 * @return 캡차 키 정보, {key: 캡차이미지키값}
	 * @throws IOException
	 */
	public Map<String, String>  captchaKey() throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			return httpClient.execute(httpRequest(captchaKeyUrl), response -> {
				String json = EntityUtils.toString(response.getEntity());
				
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {});
			});
		}
	}
	
	/**
	 * 캡차 키를 전달받아서 캡차 이미지를 반환한다.
	 * @param captchaKey 캡차 키
	 * @return 캡차 이미지 정보를 포함하는 바이트배열
	 * @throws IOException
	 */
	public byte[] captchaImage(String captchaKey) throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			return httpClient.execute(httpRequest(captchaImageUrl, captchaKey), response -> {
				return EntityUtils.toByteArray(response.getEntity());
			});
		}
	}
	
	/**
	 * 캡차 키와 사용자 입력값을 전달받아서 입력값을 검증하고 그 결과를 반환한다.
	 * @param captchaKey 캡차 키
	 * @param captchaValue 사용자가 입력한 캡차 이미지 글자값
	 * @return 검증결과, {result:불린값, responseTime:응답시간}
	 * @throws IOException
	 */
	public Map<String, Object> checkCaptcha(String captchaKey, String captchaValue) throws IOException {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			return httpClient.execute(httpRequest(captchaValidationUrl, captchaKey, captchaValue), response -> {
				String json = EntityUtils.toString(response.getEntity());
				
				ObjectMapper objectMapper = new ObjectMapper();
				return objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {});
			});
		}
	}
	
	
	/**
	 * 갭차 이미지 입력값 검증에 사용되는 정보를 포함하는 HttpRequest객체를 반환한다.
	 * @param uri 캡차이미지 검증 요청 URL
	 * @param captchaKey 캡차 키 발급시 받은 키값
	 * @param value 사용자가 입력한 캡차 이미지 글자값
	 * @return 요청URL, 요청헤더, 요청파라미터 정보가 포함된 HttpRequest객체
	 */
	private ClassicHttpRequest httpRequest(String uri, String captchaKey, String value) {
		return requestBuilder(uri)
				.addParameter("key", captchaKey)
				.addParameter("value", value)
				.build();
	}
	
	/**
	 * 갭차 이미지 요청에 사용되는 정보를 포함하는 HttpRequest객체를 반환한다.
	 * @param uri 캡차이미지 요청 URL
	 * @param captchaKey 캡차 키 발급시 받은 키값
	 * @return 요청URL, 요청헤더, 요청파라미터 정보가 포함된 HttpRequest객체
	 */
	private ClassicHttpRequest httpRequest(String uri, String captchaKey) {
		return requestBuilder(uri)
				.addParameter("key", captchaKey)
				.build();
	}
	
	/**
	 * 갭차 키 요청에 사용되는 정보를 포함하는 HttpRequest객체를 반환한다.
	 * @param uri 캡차 키 요청 URL
	 * @return 요청URL, 요청헤더 정보가 포함된 HttpRequest객체
	 */
	private ClassicHttpRequest httpRequest(String uri) {
		return requestBuilder(uri)
				.build();
	}
	
	/**
	 * 캡차 키, 캡차 이미지, 캡차 이미지 입력값 검증에 필요한 요청URL, 요청헤더 정보가 포함된 RequestBuilder객체를 반환한다.
	 * @param uri 요청 URL
	 * @return 요청URL, 요청헤더 정보가 포함된 RequestBuilder 객체
	 */
	private ClassicRequestBuilder requestBuilder(String uri) {
		return ClassicRequestBuilder.get()
				.setUri(uri)
				.addHeader("X-Naver-Client-Id", clientId)
				.addHeader("X-Naver-Client-Secret", secret);
		
	}
}
