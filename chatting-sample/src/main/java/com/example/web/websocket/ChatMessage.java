package com.example.web.websocket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {

	private String cmd;
	private String roomId;
	private String customerId;
	private String employeeId;
	private String senderType;
	private String text;
}
