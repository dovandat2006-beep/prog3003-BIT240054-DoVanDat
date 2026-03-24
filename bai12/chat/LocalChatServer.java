package com.example.bai12.chat;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

final class LocalChatServer extends WebSocketServer {
    private final Map<WebSocket, String> usernames = new ConcurrentHashMap<>();
    private final Consumer<String> logConsumer;

    LocalChatServer(int port, Consumer<String> logConsumer) {
        super(new InetSocketAddress(port));
        this.logConsumer = logConsumer;
        setReuseAddr(true);
    }

    @Override
    public void onOpen(WebSocket connection, ClientHandshake handshake) {
        log("Client moi ket noi: " + connection.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket connection, int code, String reason, boolean remote) {
        String username = usernames.remove(connection);
        if (username != null) {
            broadcast(ChatProtocol.encodeSystem(username + " da roi phong chat."));
        }
        log("Client ngat ket noi: " + connection.getRemoteSocketAddress());
    }

    @Override
    public void onMessage(WebSocket connection, String message) {
        ClientEvent event = ChatProtocol.parse(message);

        switch (event.type()) {
            case JOIN -> {
                usernames.put(connection, event.sender());
                broadcast(ChatProtocol.encodeSystem(event.sender() + " da tham gia phong chat."));
                log("Da xac nhan nguoi dung: " + event.sender());
            }
            case CHAT -> {
                String sender = usernames.getOrDefault(connection, event.sender());
                if (sender == null || sender.isBlank()) {
                    sender = "An danh";
                }
                broadcast(ChatProtocol.encodeChat(sender, event.content()));
            }
            default -> log("Bo qua su kien khong duoc ho tro: " + event.type());
        }
    }

    @Override
    public void onError(WebSocket connection, Exception exception) {
        log("Loi server: " + exception.getMessage());
    }

    @Override
    public void onStart() {
        setConnectionLostTimeout(30);
        log("Server san sang tai ws://localhost:" + getPort());
    }

    private void log(String message) {
        if (logConsumer != null) {
            logConsumer.accept(message);
        }
    }
}
