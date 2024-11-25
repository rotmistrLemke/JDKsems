package com.example.chatapp;

import javax.swing.*;
import java.awt.*;

public class ChatClientMain {
    public static void main(String[] args) throws IOException {
        SwingUtilities.invokeLater(() -> {
            ChatClient client = new ChatClient("Чат-клиент");
            client.setVisible(true);

            // Подключаемся к серверу при запуске клиента
            client.connectToServer();
        });
    }
}
