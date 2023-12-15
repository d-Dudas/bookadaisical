package com.bookadaisical.handlers;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.model.Chat;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.ChatRepository;
import com.bookadaisical.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final Map<Integer, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    public ChatWebSocketHandler(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        URI uri = session.getUri();
        String query = uri.getQuery();
        String[] queryParams = query.split("&");

        Integer userId = null;
        for(String param : queryParams) {
            if(param.startsWith("userId=")) {
                userId = Integer.parseInt(param.split("=")[1]);
                break;
            }
        }

        System.out.println("INF User id: " + userId);

        if(userId != null) {
            sessions.put(userId, session);
            System.out.println("INF Sessions: " + sessions.size());
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        MessageDto messageDto;
        try{
            messageDto = objectMapper.readValue(message.getPayload(), MessageDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(messageDto.toString());

        WebSocketSession receiverSession = sessions.get(messageDto.getReceiverId());
        if(receiverSession != null) {
            System.out.println("INF User found.");
            String jsonMessage;
            try {
                jsonMessage = objectMapper.writeValueAsString(messageDto);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle serialization error or send error response
                return;
            }
            receiverSession.sendMessage(new TextMessage(jsonMessage));
        } else {
            System.out.println("WRN User NOT found.");
        }

        Optional<User> sender = userRepository.findById(messageDto.getSenderId());
        Optional<User> receiver = userRepository.findById(messageDto.getReceiverId());
        if(sender.isPresent() && receiver.isPresent())
        {
            System.out.println("INF messsage saved");
            Chat chat = new Chat(sender.get(), receiver.get(), messageDto.getMessage(), messageDto.getSentAt());
            chatRepository.save(chat);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
        System.out.println("INF Sessions: " + sessions.size());
    }
}
