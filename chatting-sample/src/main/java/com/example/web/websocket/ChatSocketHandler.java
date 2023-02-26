package com.example.web.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChatSocketHandler extends TextWebSocketHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	private Map<String, WebSocketSession> waitingEmployeeSessions = Collections.synchronizedMap(new HashMap<>());
	private Map<String, WebSocketSession> chattingEmployeeSessions = Collections.synchronizedMap(new HashMap<>());
	private Map<String, WebSocketSession> customerSessions = Collections.synchronizedMap(new HashMap<>());

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String loginId = (String) session.getAttributes().get("LOGIN_ID");
		String loginType = (String) session.getAttributes().get("LOGIN_TYPE");
		System.out.println("로그인 아이디: ["+loginId+"], 로그인 타입: ["+loginType+"]");
		
		if ("고객".equals(loginType)) {
			customerSessions.put(loginId, session);	
		} else if ("직원".equals(loginType)) {
			waitingEmployeeSessions.put(loginId, session);
		}
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String loginId = (String) session.getAttributes().get("LOGIN_ID");
		ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);

		
		if ("start".equals(chatMessage.getCmd())) {
			
			if (waitingEmployeeSessions.size() == 0) {
				ChatMessage responseMessage = new ChatMessage();
				responseMessage.setCmd("error");
				responseMessage.setText("대기중인 상담직원이 없습니다.");
				session.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(responseMessage)));
			} else {
				String employeeId = waitingEmployeeSessions.keySet().stream().findFirst().get();
				WebSocketSession employeeSession = waitingEmployeeSessions.get(employeeId);
				chattingEmployeeSessions.put(employeeId, employeeSession);
				waitingEmployeeSessions.remove(employeeId);					
				
				ChatMessage responseMessage = new ChatMessage();
				responseMessage.setCmd("chat");
				responseMessage.setReceiver(employeeId);
				responseMessage.setText("대기중인 상담직원과 연결되었습니다.");
				session.sendMessage(new TextMessage(objectMapper.writeValueAsBytes(responseMessage)));
			}
			
			
		} else if ("stop".equals(chatMessage.getCmd())) {
			String employeeId = chatMessage.getReceiver();
			WebSocketSession employeeSession = chattingEmployeeSessions.get(employeeId);
			waitingEmployeeSessions.put(employeeId, employeeSession);
			chattingEmployeeSessions.remove(employeeId);
			
		} else if ("chat".equals(chatMessage.getCmd())) {
			String sender = chatMessage.getSender();
			if ("고객".equals(sender)) {
				String employeeId = chatMessage.getReceiver();
				
				ChatMessage responseMessage = new ChatMessage();
				responseMessage.setCmd("chat");
				responseMessage.setReceiver(loginId);
				responseMessage.setText(chatMessage.getText());
				chattingEmployeeSessions.get(employeeId).sendMessage(new TextMessage(objectMapper.writeValueAsBytes(responseMessage)));
				
			} else if ("직원".equals(sender)) {
				String customerId = chatMessage.getReceiver();
				
				ChatMessage responseMessage = new ChatMessage();
				responseMessage.setCmd("chat");
				responseMessage.setReceiver(loginId);
				responseMessage.setText(chatMessage.getText());
				customerSessions.get(customerId).sendMessage(new TextMessage(objectMapper.writeValueAsBytes(responseMessage)));
			}
		} 			
	
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String loginId = (String)session.getAttributes().get("LOGIN_ID");
		String loginType = (String) session.getAttributes().get("LOGIN_Type");
		if ("고객".equals(loginType)) {
			customerSessions.remove(loginId);			
		} else if ("직원".equals(loginType)) {
			waitingEmployeeSessions.remove(loginId);
			chattingEmployeeSessions.remove(loginId);
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		String loginId = (String)session.getAttributes().get("LOGIN_ID");
		String loginType = (String) session.getAttributes().get("LOGIN_Type");
		if ("고객".equals(loginType)) {
			customerSessions.remove(loginId);			
		} else if ("직원".equals(loginType)) {
			waitingEmployeeSessions.remove(loginId);
			chattingEmployeeSessions.remove(loginId);
		}
	}
}
