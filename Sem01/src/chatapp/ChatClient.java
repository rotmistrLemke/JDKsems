package com.example.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ChatClient extends JFrame {
    private JTextArea chatHistory;
    private JTextField messageField;
    private JButton sendButton;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private static final String HISTORY_FILE = "chat_history.txt";

    public ChatClient(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 600);

        chatHistory = new JTextArea();
        chatHistory.setEditable(false);
        add(new JScrollPane(chatHistory), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        messageField = new JTextField(30);
        sendButton = new JButton("Отправить");

        inputPanel.add(messageField);
        inputPanel.add(sendButton);
        add(inputPanel, BorderLayout.SOUTH);

        loadChatHistory();

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    public void connectToServer() {
        try {
            socket = new Socket("localhost", 55555);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            Thread receiverThread = new Thread(new MessageReceiver());
            receiverThread.start();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка подключения к серверу");
        }
    }

    public void disconnectFromServer() {
        try {
            writer.close();
            reader.close();
            socket.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка отключения от сервера");
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            writer.println(message);
            appendToChatHistory("Вы: " + message);
            saveChatHistory();
            messageField.setText("");
        }
    }

    private void loadChatHistory() {
        try (BufferedReader br = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                chatHistory.append(line + "\n");
            }
        } catch (IOException e) {
            // Файл не существует или ошибка чтения
        }
    }

    private void saveChatHistory() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(HISTORY_FILE))) {
            pw.print(chatHistory.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ошибка сохранения истории чата");
        }
    }

    private void appendToChatHistory(String message) {
        chatHistory.append(message + "\n");
        chatHistory.setCaretPosition(chatHistory.getDocument().getLength());
        saveChatHistory(); // Сохраняем историю после каждого нового сообщения
    }

    private class MessageReceiver implements Runnable {
        @Override
        public void run() {
            try {
                String message;
                while ((message = reader.readLine()) != null) {
                    SwingUtilities.invokeLater(() -> appendToChatHistory(message));
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(ChatClient.this, "Соединение прервано");
            } finally {
                disconnectFromServer();
            }
        }
    }
}
