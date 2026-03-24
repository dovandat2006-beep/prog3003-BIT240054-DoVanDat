package com.example.bai12.chat;

import java.net.URI;
import java.util.function.Consumer;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

final class LocalChatClient extends WebSocketClient {
    private final String username;
    private final Consumer<ClientEvent> eventConsumer;

    LocalChatClient(URI serverUri, String username, Consumer<ClientEvent> eventConsumer) {
        super(serverUri);
        this.username = username;
        this.eventConsumer = eventConsumer;
    }

    @Override
    public void onOpen(ServerHandshake handshakeData) {
        publish(new ClientEvent(ChatEventType.CONNECTED, null, "Da ket noi toi server."));
        send(ChatProtocol.encodeJoin(username));
    }

    @Override
    public void onMessage(String message) {
        try {
            publish(ChatProtocol.parse(message));
        } catch (IllegalArgumentException exception) {
            publish(new ClientEvent(ChatEventType.ERROR, null, exception.getMessage()));
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        String detail = reason == null || reason.isBlank() ? "Da dong ket noi." : reason;
        publish(new ClientEvent(ChatEventType.DISCONNECTED, null, detail));
    }

    @Override
    public void onError(Exception exception) {
        publish(new ClientEvent(ChatEventType.ERROR, null, exception.getMessage()));
    }

    void sendChat(String content) {
        send(ChatProtocol.encodeChat(username, content));
    }

    private void publish(ClientEvent event) {
        if (eventConsumer != null) {
            eventConsumer.accept(event);
        }
    }
}
