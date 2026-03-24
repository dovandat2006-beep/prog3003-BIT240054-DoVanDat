package com.example.bai12.chat;

import java.net.URI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Bai 2: Phan mem chat local giua 2 nguoi bang JavaFX va WebSocket
public class ChatApplication extends Application {
    private TextField usernameField;
    private TextField hostField;
    private TextField portField;
    private TextField messageField;
    private TextArea chatArea;
    private Label statusLabel;
    private Button startServerButton;
    private Button stopServerButton;
    private Button connectButton;
    private Button disconnectButton;
    private Button sendButton;

    private LocalChatServer server;
    private LocalChatClient client;

    @Override
    public void start(Stage stage) {
        usernameField = new TextField("User-" + System.currentTimeMillis() % 1000);
        hostField = new TextField("localhost");
        portField = new TextField("8080");
        messageField = new TextField();

        chatArea = new TextArea();
        chatArea.setEditable(false);
        chatArea.setWrapText(true);

        statusLabel = new Label("Trang thai: Chua ket noi");

        startServerButton = new Button("Start Server");
        stopServerButton = new Button("Stop Server");
        connectButton = new Button("Connect");
        disconnectButton = new Button("Disconnect");
        sendButton = new Button("Send");

        stopServerButton.setDisable(true);
        disconnectButton.setDisable(true);
        sendButton.setDisable(true);

        startServerButton.setOnAction(event -> startServer());
        stopServerButton.setOnAction(event -> stopServer());
        connectButton.setOnAction(event -> connectToServer());
        disconnectButton.setOnAction(event -> disconnectFromServer());
        sendButton.setOnAction(event -> sendMessage());
        messageField.setOnAction(event -> sendMessage());

        BorderPane root = new BorderPane();
        root.setTop(buildTopPanel());
        root.setCenter(chatArea);
        root.setBottom(buildBottomPanel());
        root.setPadding(new Insets(16));

        stage.setTitle("Bai 2 - Local Chat JavaFX + WebSocket");
        stage.setScene(new Scene(root, 760, 520));
        stage.show();

        stage.setOnCloseRequest(event -> {
            disconnectFromServer();
            stopServer();
        });

        appendLine("===== BAI 2: LOCAL CHAT JAVAFX + WEBSOCKET =====");
        appendLine("Huong dan nhanh:");
        appendLine("1. Mo cua so thu nhat, bam Start Server.");
        appendLine("2. Bam Connect tren cua so thu nhat de vao phong chat.");
        appendLine("3. Chay them 1 instance nua, doi username va bam Connect.");
        appendLine("4. Hai cua so se chat voi nhau qua localhost.");
    }

    private VBox buildTopPanel() {
        TextField[] growingFields = {usernameField, hostField, portField};
        for (TextField field : growingFields) {
            HBox.setHgrow(field, Priority.ALWAYS);
        }

        HBox connectionRow = new HBox(10,
                labeledBox("Username", usernameField),
                labeledBox("Host", hostField),
                labeledBox("Port", portField));
        connectionRow.setAlignment(Pos.CENTER_LEFT);

        HBox controlRow = new HBox(10,
                startServerButton,
                stopServerButton,
                connectButton,
                disconnectButton,
                statusLabel);
        controlRow.setAlignment(Pos.CENTER_LEFT);

        VBox topPanel = new VBox(12, connectionRow, controlRow);
        topPanel.setPadding(new Insets(0, 0, 12, 0));
        return topPanel;
    }

    private HBox buildBottomPanel() {
        HBox.setHgrow(messageField, Priority.ALWAYS);
        HBox bottomPanel = new HBox(10, messageField, sendButton);
        bottomPanel.setAlignment(Pos.CENTER_LEFT);
        bottomPanel.setPadding(new Insets(12, 0, 0, 0));
        return bottomPanel;
    }

    private VBox labeledBox(String labelText, TextField field) {
        Label label = new Label(labelText);
        VBox box = new VBox(6, label, field);
        box.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(box, Priority.ALWAYS);
        return box;
    }

    private void startServer() {
        if (server != null) {
            appendLine("[Server] Server dang chay.");
            return;
        }

        try {
            int port = Integer.parseInt(portField.getText().trim());
            server = new LocalChatServer(port, this::appendServerLog);
            server.start();

            startServerButton.setDisable(true);
            stopServerButton.setDisable(false);
            appendLine("[Server] Dang khoi dong server...");
        } catch (NumberFormatException exception) {
            appendLine("[Loi] Port khong hop le.");
        } catch (Exception exception) {
            appendLine("[Loi] Khong the khoi dong server: " + exception.getMessage());
            server = null;
        }
    }

    private void stopServer() {
        if (server == null) {
            return;
        }

        try {
            server.stop(1000);
            appendLine("[Server] Da dung server.");
        } catch (Exception exception) {
            appendLine("[Loi] Khong the dung server: " + exception.getMessage());
        } finally {
            server = null;
            startServerButton.setDisable(false);
            stopServerButton.setDisable(true);
        }
    }

    private void connectToServer() {
        if (client != null) {
            appendLine("[Thong bao] Dang trong qua trinh ket noi hoac da ket noi server.");
            return;
        }

        String username = usernameField.getText().trim();
        String host = hostField.getText().trim();

        if (username.isBlank()) {
            appendLine("[Loi] Username khong duoc de trong.");
            return;
        }

        try {
            int port = Integer.parseInt(portField.getText().trim());
            URI uri = new URI("ws://" + host + ":" + port);
            client = new LocalChatClient(uri, username, this::handleClientEvent);
            client.connect();
            statusLabel.setText("Trang thai: Dang ket noi...");
            connectButton.setDisable(true);
            disconnectButton.setDisable(false);
            appendLine("[Client] Dang ket noi den " + uri);
        } catch (NumberFormatException exception) {
            appendLine("[Loi] Port khong hop le.");
        } catch (Exception exception) {
            appendLine("[Loi] Khong the ket noi: " + exception.getMessage());
            connectButton.setDisable(false);
            disconnectButton.setDisable(true);
            client = null;
        }
    }

    private void disconnectFromServer() {
        if (client == null) {
            return;
        }

        client.close();
    }

    private void sendMessage() {
        if (client == null || !client.isOpen()) {
            appendLine("[Loi] Chua ket noi den server.");
            return;
        }

        String message = messageField.getText().trim();
        if (message.isBlank()) {
            return;
        }

        client.sendChat(message);
        messageField.clear();
    }

    private void handleClientEvent(ClientEvent event) {
        Platform.runLater(() -> {
            switch (event.type()) {
                case CONNECTED -> {
                    statusLabel.setText("Trang thai: Da ket noi");
                    connectButton.setDisable(true);
                    disconnectButton.setDisable(false);
                    sendButton.setDisable(false);
                    appendLine("[Client] " + event.content());
                }
                case DISCONNECTED -> {
                    statusLabel.setText("Trang thai: Chua ket noi");
                    connectButton.setDisable(false);
                    disconnectButton.setDisable(true);
                    sendButton.setDisable(true);
                    appendLine("[Client] " + event.content());
                    client = null;
                }
                case SYSTEM -> appendLine("[He thong] " + event.content());
                case CHAT -> appendLine(event.sender() + ": " + event.content());
                case ERROR -> appendLine("[Loi] " + event.content());
                default -> appendLine("[Thong bao] Su kien khong xac dinh.");
            }
        });
    }

    private void appendServerLog(String message) {
        Platform.runLater(() -> appendLine("[Server] " + message));
    }

    private void appendLine(String text) {
        chatArea.appendText(text + System.lineSeparator());
    }
}
