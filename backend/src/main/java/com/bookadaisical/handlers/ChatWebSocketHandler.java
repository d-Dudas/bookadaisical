package com.bookadaisical.handlers;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bookadaisical.dto.MessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // String receivedMessage = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MessageDto messageDto;
        try{
            messageDto = objectMapper.readValue(message.getPayload(), MessageDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // System.out.println("Message got: " + receivedMessage);
        System.out.println(messageDto.toString());
        // Thread.sleep(4000);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        messageDto.setSenderId(2);
        messageDto.setReceiverId(1);
        messageDto.setSentAt(LocalDateTime.now());
        messageDto.setMessage("Got this message: " + messageDto.getMessage());
        String jsonMessage;
        try {
            jsonMessage = objectMapper.writeValueAsString(messageDto);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle serialization error or send error response
            return;
        }
        session.sendMessage(new TextMessage(jsonMessage));
        // session.sendMessage(new TextMessage("Message got: " + receivedMessage));
    }
}
