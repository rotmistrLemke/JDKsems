

import java.io.IOException;

public class ChatServerMain {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer(55555);
            server.start();
        } catch (IOException e) {
            System.err.println("Ошибка при запуске сервера: " + e.getMessage());
        }
    }
}
