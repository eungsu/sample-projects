package com.example.web.websocket;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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

	// 웹 소켓 연결요청이 완료되면 실행되는 메소드다.
	// 웹 소켓 연결요청이 완료되면 해당 클라이언트와 통신을 담당하는 WebSocketSession을 고객과 직원으로 구분해서 Map객체에 저장한다.
	// Map객체에 저장할 때는 고객이나 직원의 아이디를 key로 WebSocketSession을 value로 저장한다.
	// customerSessions => [{"hong":WebSocketSession}, {"kim":WebSocketSession }, {"kang":WebSocketSession}]
	// waitingEmployeeSessions => [{"emp1":WebSocketSession}, {"emp2":WebSocketSession}]
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String loginId = (String) session.getAttributes().get("LOGIN_ID");
		String loginType = (String) session.getAttributes().get("LOGIN_TYPE");
		
		if ("고객".equals(loginType)) {
			customerSessions.put(loginId, session);	
		} else if ("직원".equals(loginType)) {
			waitingEmployeeSessions.put(loginId, session);
		}
		
	}
	
	// 클라이언트로부터 웹소켓으로 메세지를 수신하면 실행되는 메소드다.
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
	
	// 클라이언트와 웹 소켓 연결이 종료되면 실행되는 메소드다.
	// Map객체에 저장된 WebSocketSession 세션을 삭제한다.
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

	// 클라이언트와 웹 소켓을 통해서 메세지 교환 중 오류가 발생하면 실행되는 메소드다.
	// Map객체에 저장된 WebSocketSession 세션을 삭제한다.
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
