package com.bookadaisical.handlers;

import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.bookadaisical.dto.MessageDto;
import com.bookadaisical.exceptions.UriNotFoundException;
import com.bookadaisical.exceptions.UserNotFoundException;
import com.bookadaisical.model.Chat;
import com.bookadaisical.model.User;
import com.bookadaisical.repository.ChatRepository;
import com.bookadaisical.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    public ChatWebSocketHandler(ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
        this.objectMapper = new ObjectMapper()
                            .registerModule(new JavaTimeModule());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        URI uri = session.getUri();
        if(uri == null) {
            throw new UriNotFoundException();
        }

        String query = uri.getQuery();
        String[] queryParams = query.split("&");

        UUID userId = null;
        for(String param : queryParams) {
            if(param.startsWith("userId=")) {
                userId = UUID.fromString(param.split("=")[1]);
                break;
            }
        }

        if(userId == null) {
            session.close(CloseStatus.BAD_DATA);
            return;
        }

        sessions.put(userId, session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        MessageDto messageDto = parseMessage(message);

        User sender = validateUser(messageDto.getSenderId());
        User receiver = validateUser(messageDto.getReceiverId());

        saveMessage(sender, receiver, messageDto);

        sendMessageToReceiver(receiver.getId(), messageDto);
    }

    private MessageDto parseMessage(TextMessage message) throws IOException {
        try {
            return objectMapper.readValue(message.getPayload(), MessageDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User validateUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    private void saveMessage(User sender, User receiver, MessageDto messageDto) {
        Chat chat = new Chat(sender, receiver, messageDto.getMessage(), messageDto.getSentAt());
        chatRepository.save(chat);
    }

    private void sendMessageToReceiver(UUID receiverId, MessageDto messageDto) throws IOException {
        WebSocketSession receiverSession = sessions.get(receiverId);
        if (receiverSession != null) {
            String jsonMessage = objectMapper.writeValueAsString(messageDto);
            receiverSession.sendMessage(new TextMessage(jsonMessage));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.values().remove(session);
    }
}
