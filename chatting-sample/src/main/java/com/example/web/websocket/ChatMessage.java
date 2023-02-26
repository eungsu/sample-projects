package com.example.web.websocket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessage {

	private String cmd;
	private String receiver;
	private String sender;
	private String text;
}
