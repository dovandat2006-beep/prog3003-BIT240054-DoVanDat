package com.example.bai12.chat;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

final class ChatProtocol {
    private static final String SEPARATOR = "|";

    private ChatProtocol() {
    }

    static String encodeJoin(String username) {
        return ChatEventType.JOIN.name() + SEPARATOR + encode(username);
    }

    static String encodeChat(String sender, String content) {
        return ChatEventType.CHAT.name() + SEPARATOR + encode(sender) + SEPARATOR + encode(content);
    }

    static String encodeSystem(String content) {
        return ChatEventType.SYSTEM.name() + SEPARATOR + encode(content);
    }

    static ClientEvent parse(String rawMessage) {
        try {
            String[] parts = rawMessage.split("\\|", -1);
            ChatEventType type = ChatEventType.valueOf(parts[0]);

            return switch (type) {
                case JOIN -> new ClientEvent(type, decode(parts[1]), null);
                case CHAT -> new ClientEvent(type, decode(parts[1]), decode(parts[2]));
                case SYSTEM -> new ClientEvent(type, null, decode(parts[1]));
                case CONNECTED, DISCONNECTED, ERROR -> throw new IllegalArgumentException("Unsupported wire type: " + type);
            };
        } catch (RuntimeException exception) {
            throw new IllegalArgumentException("Tin nhan khong hop le: " + rawMessage, exception);
        }
    }

    private static String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    private static String decode(String value) {
        return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
    }
}
